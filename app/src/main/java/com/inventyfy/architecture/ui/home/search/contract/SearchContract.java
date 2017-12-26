package com.inventyfy.architecture.ui.home.search.contract;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;

import java.util.List;

public interface SearchContract {

    interface Presenter extends BasePresenter {

        LiveData<ResourcesResponse<List<ResultTable>>> getAllSearchResult();
    }
}
