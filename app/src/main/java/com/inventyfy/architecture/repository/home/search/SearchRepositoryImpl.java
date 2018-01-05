package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.di.common.ApplicationScope;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.ResponseResult;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.repository.NetworkBound;
import com.inventyfy.architecture.repository.RepositoryBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

@ApplicationScope
public class SearchRepositoryImpl implements SearchRepository {

    AppExecutors appExecutors;
    SearchService searchService;

    private SearchDao searchDao;

    private RateLimiter<String> rateLimiter = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    SearchRepositoryImpl(SearchService searchService, AppExecutors appExecutors) {
        this.searchService = searchService;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<ResourcesResponse<ResponseResult>> getSearchResult(final String searchQuery, final String country,
                                                                       final String media, final String entity) {
        RepositoryBuilder<ResponseResult> builder = new RepositoryBuilder<>(appExecutors);
        builder.setNetworkResponse(searchService.getSearchResult(searchQuery))
                .setSaveResult(true)
                .setRateLimiter(rateLimiter);

        return NetworkBound.setBuilder(builder);
    }
}
