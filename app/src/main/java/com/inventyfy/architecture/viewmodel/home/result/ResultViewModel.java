package com.inventyfy.architecture.viewmodel.home.result;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.AbsentLiveData;
import com.inventyfy.architecture.repository.home.result.ResultRepository;
import com.inventyfy.architecture.repository.home.result.ResultRepositoryImpl;
import com.inventyfy.architecture.ui.home.search.contract.ResultContract;

import java.util.List;

import javax.inject.Inject;

public class ResultViewModel extends AbstractViewModel<ResultContract.Presenter> implements ResultContract.Presenter {

    private ResultRepository resultRepository;

    private LiveData<List<ResultTable>> resultTables;

    @Inject
    ResultViewModel(ResultRepositoryImpl resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public ResultContract.Presenter getPresenter() {
        return this;
    }

    @Override
    public LiveData<List<ResultTable>> getAllResultFromSearchId(int searchId) {
        resultTables = Transformations.switchMap(resultRepository.getAllResultFromSearchId(searchId), input -> {
            if (input == null) {
                return AbsentLiveData.create();
            } else {
                MutableLiveData<List<ResultTable>> reslutTable = new MutableLiveData<>();
                reslutTable.setValue(input.data);
                return reslutTable;
            }
        });
//        return resultRepository.getAllResultFromSearchId(searchId);
        return resultTables;
    }

}
