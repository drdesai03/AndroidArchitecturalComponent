package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.WorkerThread;

import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.support.ApiResponse;

public class NetworkBound<R> {

    private MediatorLiveData<ResourcesResponse<R>> result = new MediatorLiveData<>();
    private RepositoryBuilder<R> builder;
    private SaveResultCallback<R> saveResultCallback;

    private NetworkBound(RepositoryBuilder<R> builder) {
        this.builder = builder;
    }

    public static <R> NetworkBound<R> setBuilder(RepositoryBuilder<R> builder) {
        return new NetworkBound<>(builder);
    }

    public static <R> NetworkBound<R> setBuilder(RepositoryBuilder<R> builder,
                                                 SaveResultCallback<R> resultCallback) {
        NetworkBound<R> networkBound = new NetworkBound<>(builder);
        networkBound.setSaveResultCallback(resultCallback);
        return networkBound;
    }

    private void getResponse() {
        if (builder.getDbSource() == null) {
            fetchFromNetwork(false);
        } else {
            result.addSource(builder.getDbSource(), response -> {
                this.result.removeSource(builder.getDbSource());
                if (shouldFetchResult(builder.getKeywork(), response) || builder.getNetworkResponse() != null) {
                    fetchFromNetwork(true);
                } else {
                    result.addSource(builder.getDbSource(), ResourcesResponse::success);
                    result.setValue(ResourcesResponse.success(response));
                }
            });
        }
    }

    private void fetchFromNetwork(final boolean isDbRequired) {
        if (isDbRequired) {
            result.addSource(builder.getDbSource(), newResponse -> {
                result.removeSource(builder.getDbSource());
                result.setValue(ResourcesResponse.loading(newResponse));
            });
        } else {
            if (builder.getNetworkResponse() == null) {
                throw new IllegalArgumentException("Please provide network response LiveData from your builder class");
            }
        }

        result.addSource(builder.getNetworkResponse(), (ApiResponse<R> requestApiResponse) -> {
            result.removeSource(builder.getNetworkResponse());
            if (requestApiResponse != null) {
                if (requestApiResponse.isSuccess()) {
                    if (builder.isShouldSaveResultToDatabase()) {
                        //TODO : save result to database
                        if (saveResultCallback != null) {
                            builder.getAppExecutors().getDiskOp().execute(() -> {
                                saveResultCallback.saveResult(processResult(requestApiResponse));

                                builder.getAppExecutors().getMainThread().execute(() ->
                                        result.setValue(ResourcesResponse.success(processResult(requestApiResponse))));
                            });
                        } else {
                            throw new IllegalArgumentException("Please build your network client with saveResult callback");
                        }
                    }
                } else {
                    if (isDbRequired) {
                        result.addSource(builder.getDbSource(), newResponse -> {
                            result.setValue(ResourcesResponse.error(requestApiResponse.errorMessage,
                                    newResponse));
                        });
                    } else {
                        result.setValue(ResourcesResponse.error(requestApiResponse.errorMessage,
                                processResult(requestApiResponse)));
                    }
                }
            }
        });
    }

    public LiveData<ResourcesResponse<R>> asLiveData() {
        getResponse();
        return result;
    }

    @WorkerThread
    private R processResult(ApiResponse<R> response) {
        return response.body;
    }

    private void setSaveResultCallback(SaveResultCallback<R> saveResultCallback) {
        this.saveResultCallback = saveResultCallback;
    }

    public interface SaveResultCallback<R> {
        void saveResult(R result);
    }

    private boolean shouldFetchResult(String key, R data) {
        return data == null || (builder.getRateLimiter() != null && builder.getRateLimiter().shouldFetch(key));
    }
}
