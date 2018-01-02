package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.RateLimiter;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.network.support.ApiResponse;
import com.inventyfy.architecture.repository.NetworkBoundResource;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class SearchRepositoryImpl implements SearchRepository {

    @Inject
    AppExecutors appExecutors;
    @Inject
    SearchService searchService;

    private SearchDao searchDao;

    private RateLimiter<String> rateLimiter = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    SearchRepositoryImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public LiveData<ResourcesResponse<ResultTable>> getSearchResult(final String searchQuery, final String country,
                                                                    final String media, final String entity) {
        return new NetworkBoundResource<ResultTable, ResultTable>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull ResultTable item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable ResultTable data) {
                return data == null || rateLimiter.shouldFetch(searchQuery);
            }

            @NonNull
            @Override
            protected LiveData<ResultTable> loadFromDb() {
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
