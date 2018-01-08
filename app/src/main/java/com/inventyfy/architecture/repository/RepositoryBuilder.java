package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.network.support.ApiResponse;

public class RepositoryBuilder<R> {

    private String keywork;
    private AppExecutors appExecutors;
    private LiveData<R> dbSource;
    private LiveData<ApiResponse<R>> networkResponse;
    private RateLimiter<String> rateLimiter;
    private boolean shouldSaveResultToDatabase;

    public RepositoryBuilder(String keywork, AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.keywork = keywork;
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

    public RepositoryBuilder setShouldSaveResultToDatabase(boolean shouldSaveResultToDatabase) {
        this.shouldSaveResultToDatabase = shouldSaveResultToDatabase;
        return this;
    }

    AppExecutors getAppExecutors() {
        return appExecutors;
    }

    String getKeywork() {
        return keywork;
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

    boolean isShouldSaveResultToDatabase() {
        return shouldSaveResultToDatabase;
    }
}
