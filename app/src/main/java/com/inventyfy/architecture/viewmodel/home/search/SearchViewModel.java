package com.inventyfy.architecture.viewmodel.home.search;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.ui.home.search.contract.SearchContract;

import java.util.List;

public class SearchViewModel extends AbstractViewModel<SearchContract.Presenter> implements SearchContract.Presenter {

    private LiveData<ResourcesResponse<List<ResultTable>>> resultResponse;

    @Override
    public SearchContract.Presenter getPresenter() {
        return this;
    }

    @Override
    public LiveData<ResourcesResponse<List<ResultTable>>> getAllSearchResult() {
        return resultResponse;
    }
}
