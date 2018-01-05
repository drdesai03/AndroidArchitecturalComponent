package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.WorkerThread;

import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.support.ApiResponse;

public class NetworkBound<R> {

    private MediatorLiveData<ResourcesResponse<R>> result = new MediatorLiveData<>();
    private RepositoryBuilder<R> builder;

    private NetworkBound(RepositoryBuilder<R> builder) {
        this.builder = builder;
    }

    public static <R> LiveData<ResourcesResponse<R>> setBuilder(RepositoryBuilder<R> builder) {
        NetworkBound<R> networkBound = new NetworkBound<>(builder);
        networkBound.getResponse();
        return networkBound.asLiveData();
    }

    private void getResponse() {
        if (builder.getDbSource() == null) {
            //There is no database interaction involves
            fetchFromNetwork(false);
        } else {
            //Database involvement
        }
    }

    private void fetchFromNetwork(final boolean isDbRequired) {
        if (isDbRequired) {

        } else {
            result.addSource(builder.getNetworkResponse(), requestApiResponse -> {
                if (requestApiResponse != null) {
                    if (requestApiResponse.isSuccess()) {
                        if(builder.isSaveResult()) {
                            //TODO : save result to database
                        }
                        result.setValue(ResourcesResponse.success(processResult(requestApiResponse)));
                    }
                }
            });
        }
    }

    public LiveData<ResourcesResponse<R>> asLiveData() {
        return result;
    }

    @WorkerThread
    private R processResult(ApiResponse<R> response) {
        return response.body;
    }

}
