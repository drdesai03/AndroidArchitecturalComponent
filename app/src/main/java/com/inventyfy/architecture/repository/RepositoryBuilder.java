package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.network.support.ApiResponse;

public class RepositoryBuilder<R> {

    private AppExecutors appExecutors;
    private LiveData<R> dbSource;
    private LiveData<ApiResponse<R>> networkResponse;
    private RateLimiter<String> rateLimiter;
    private boolean isSaveResult;

    public RepositoryBuilder(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public RepositoryBuilder setDbSource(LiveData<R> dbSource) {
        this.dbSource = dbSource;
        return this;
    }

    public RepositoryBuilder setNetworkResponse(LiveData<ApiResponse<R>> networkResponse) {
        this.networkResponse = networkResponse;
        return this;
    }

    public RepositoryBuilder setRateLimiter(RateLimiter<String> rateLimiter) {
        this.rateLimiter = rateLimiter;
        return this;
    }

    public RepositoryBuilder setSaveResult(boolean saveResult) {
        isSaveResult = saveResult;
        return this;
    }

    AppExecutors getAppExecutors() {
        return appExecutors;
    }

    LiveData<R> getDbSource() {
        return dbSource;
    }

    LiveData<ApiResponse<R>> getNetworkResponse() {
        return networkResponse;
    }

    RateLimiter<String> getRateLimiter() {
        return rateLimiter;
    }

    boolean isSaveResult() {
        return isSaveResult;
    }
}
