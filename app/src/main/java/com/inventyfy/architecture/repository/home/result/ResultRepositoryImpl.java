package com.inventyfy.architecture.repository.home.result;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.AppDatabase;
import com.inventyfy.architecture.database.dao.ResultDao;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.di.common.ApplicationScope;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.repository.NetworkBound;
import com.inventyfy.architecture.repository.RepositoryBuilder;

import java.util.List;

import javax.inject.Inject;

@ApplicationScope
public class ResultRepositoryImpl implements ResultRepository {

    private AppExecutors appExecutors;
    private AppDatabase appDatabase;
    private ResultDao resultDao;

    @Inject
    ResultRepositoryImpl(AppExecutors appExecutors, AppDatabase appDatabase, ResultDao resultDao) {
        this.appExecutors = appExecutors;
        this.appDatabase = appDatabase;
        this.resultDao = resultDao;
    }

    @Override
    public LiveData<ResourcesResponse<List<ResultTable>>> getAllResultFromSearchId(int searchId) {
        RepositoryBuilder<List<ResultTable>> builder = new RepositoryBuilder<>("RESULT", appExecutors);
        builder.setDbSource(resultDao.getAllResult(searchId));

        NetworkBound<List<ResultTable>> responseResultNetworkBound = NetworkBound.setBuilder(builder);
        return responseResultNetworkBound.asLiveData();
    }
}
