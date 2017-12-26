package com.inventyfy.architecture.repository;

import com.inventyfy.architecture.helper.RateLimiter;

public abstract class AbstractRepositoryBound<R> {

    private boolean isRequiredToFetchFromNetwork(R result) {
        return result == null || getRateLimiter() == null || getRateLimiter().shouldFetch("search");
    }

    /**
     * Give rate limited instance from you implementation if you want to restrict network call
     * specific time
     *
     * @return RateLimiter
     */
    protected abstract <T> RateLimiter<T> getRateLimiter();
}
