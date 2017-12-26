package com.inventyfy.architecture.repository.home.search;

import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.repository.AbstractRepositoryBound;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchRepository extends AbstractRepositoryBound {

    private SearchService searchService;
    private SearchDao searchDao;
    private final AppExecutors appExecutors;

    private RateLimiter<String> rateLimiter = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    SearchRepository(final AppExecutors appExecutors, final SearchDao searchDao, final SearchService searchService) {
        this.appExecutors = appExecutors;
        this.searchDao = searchDao;
        this.searchService = searchService;
    }

    @Override
    protected RateLimiter<String> getRateLimiter() {
        return rateLimiter;
    }
}
