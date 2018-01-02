package com.inventyfy.architecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;

import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.support.ApiResponse;

public abstract class AbstractRepositoryBound {

    private AppExecutors appExecutors;
    private boolean isRateLimiterRequired;

    @MainThread
    public AbstractRepositoryBound(final AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public <T> LiveData<ResourcesResponse<T>> fetchData(LiveData<T> dbSource, boolean isRateLimiterRequired,
                                                        LiveData<ApiResponse<T>> requestType) {
        MediatorLiveData<ResourcesResponse<T>> result = new MediatorLiveData<>();
        this.isRateLimiterRequired = isRateLimiterRequired;
        result.setValue(ResourcesResponse.loading(null));
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (isRequiredToFetchFromNetwork("", data)) {
                //Required to call network
            } else {
                //Return result with success
                result.addSource(dbSource, finalResult -> setValue(result, ResourcesResponse.success(data)));
            }
        });
        return result;
    }

    @MainThread
    private <T> void setValue(MediatorLiveData<ResourcesResponse<T>> resultDetails, ResourcesResponse<T> newValue) {
        if (!checkObjectEquals(resultDetails.getValue(), newValue)) {
            resultDetails.setValue(newValue);
        }
    }

    private <T> void fetchFromNetwork(MediatorLiveData<ResourcesResponse<T>> resultDetails, final LiveData<T> dbSource,
                                      LiveData<ApiResponse<T>> requestType) {
        resultDetails.addSource(dbSource, newData -> setValue(resultDetails, ResourcesResponse.loading(newData)));
        resultDetails.addSource(requestType, response -> {
            resultDetails.removeSource(dbSource);
            resultDetails.removeSource(requestType);

            if (response.isSuccess()) {

            } else {
                resultDetails.addSource(dbSource, finalResult -> setValue(resultDetails,
                        ResourcesResponse.error(response.errorMessage, finalResult)));
            }
        });
    }

    private <T> boolean isRequiredToFetchFromNetwork(final String key, T result) {
        return result == null || (isRateLimiterRequired && (getRateLimiter() == null && getRateLimiter().shouldFetch(key)));
    }

    private boolean checkObjectEquals(Object obj1, Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    /**
     * Give rate limited instance from you implementation if you want to restrict network call
     * specific time
     *
     * @return RateLimiter
     */
    protected abstract <T> RateLimiter<T> getRateLimiter();
}
