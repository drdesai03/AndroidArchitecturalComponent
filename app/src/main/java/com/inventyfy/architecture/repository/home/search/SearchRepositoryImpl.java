package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.di.common.ApplicationScope;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.network.support.ApiResponse;
import com.inventyfy.architecture.repository.NetworkBoundResource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

@ApplicationScope
public class SearchRepositoryImpl implements SearchRepository {

    AppExecutors appExecutors;
    SearchService searchService;

    private SearchDao searchDao;

    private RateLimiter<String> rateLimiter = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    SearchRepositoryImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public LiveData<ResourcesResponse<List<ResultTable>>> getSearchResult(final String searchQuery, final String country,
                                                                          final String media, final String entity) {
        return new NetworkBoundResource<ResultTable, List<ResultTable>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull ResultTable item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<ResultTable> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<ResultTable>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ResultTable>> createCall() {
                return searchService.getSearchResult(searchQuery, country, media, entity);
            }
        }.asLiveData();
    }
}
