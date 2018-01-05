package com.inventyfy.architecture.repository.home.search;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.inventyfy.architecture.database.AppDatabase;
import com.inventyfy.architecture.database.dao.ResultDao;
import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.database.table.SearchTable;
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
    private ResultDao resultDao;
    private AppDatabase appDatabase;

    @Inject
    SearchRepositoryImpl(SearchService searchService, AppExecutors appExecutors, AppDatabase appDatabase, SearchDao searchDao, ResultDao resultDao) {
        this.searchService = searchService;
        this.appExecutors = appExecutors;
        this.searchDao = searchDao;
        this.resultDao = resultDao;
        this.appDatabase = appDatabase;
    }

    @Override
    public LiveData<ResourcesResponse<ResponseResult>> getSearchResult(final String searchQuery, final String country,
                                                                       final String media, final String entity) {
        RepositoryBuilder<ResponseResult> builder = new RepositoryBuilder<>(appExecutors);
        builder.setNetworkResponse(searchService.getSearchResult(searchQuery))
                .setSaveResult(true)
                .setRateLimiter(rateLimiter);

        NetworkBound<ResponseResult> responseResultNetworkBound = NetworkBound.setBuilder(builder);
        responseResultNetworkBound.setSaveResultCallback(result -> {
            //TODO : Save result to database;
            appExecutors.getDiskOp().execute(() -> {
                SearchTable searchTable = new SearchTable();
                searchTable.setSearchText(searchQuery);
                long id = searchDao.insertSearchQuery(searchTable);
                for (int i = 0; i < result.getResults().size(); i++) {
                    result.getResults().get(i).setSearchId((int) id);
                }
                appDatabase.beginTransaction();
                try {
                    resultDao.insert(result.getResults());
                    appDatabase.setTransactionSuccessful();
                } finally {
                    appDatabase.endTransaction();
                }
            });
        });
        return responseResultNetworkBound.asLiveData();
    }
}
