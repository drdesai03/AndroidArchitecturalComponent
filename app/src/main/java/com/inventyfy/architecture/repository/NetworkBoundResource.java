package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.support.ApiResponse;

import java.util.Objects;

public abstract class NetworkBoundResource<Request, Response> {
    private final AppExecutors appExecutors;

    private final MediatorLiveData<ResourcesResponse<Response>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(ResourcesResponse.loading(null));
        LiveData<Response> dbSource = loadFromDb();
//        result.addSource(dbSource, data -> {
//            result.removeSource(dbSource);
        if (shouldFetch(null)) {
            fetchFromNetwork(dbSource);
//            } else {
//                result.addSource(dbSource, newData -> setValue(ResourcesResponse.success(newData)));
//            }
//        });
        }
    }

    @MainThread
    private void setValue(ResourcesResponse<Response> newValue) {
        if (!checkObjectEquals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<Response> dbSource) {
        LiveData<ApiResponse<Request>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
//        result.addSource(dbSource, newData -> setValue(ResourcesResponse.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
//            result.removeSource(dbSource);
            //noinspection ConstantConditions
            if (response.isSuccess()) {
                appExecutors.getDiskOp().execute(() -> {
                    saveCallResult(processResponse(response));
                    appExecutors.getMainThread().execute(() ->
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource(loadFromDb(),
                                    response1 -> setValue(ResourcesResponse.success(response1)))
                    );
                });
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setValue(ResourcesResponse.error(response.errorMessage, newData)));
            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<ResourcesResponse<Response>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected Request processResponse(ApiResponse<Request> response) {
        return response.body;
    }

    private boolean checkObjectEquals(final Object obj1, final Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        }

        if (obj2 == null) {
            return false;
        }

        return obj1.equals(obj2);
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull Request item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable Response data);

    @NonNull
    @MainThread
    protected abstract LiveData<Response> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<Request>> createCall();
}
