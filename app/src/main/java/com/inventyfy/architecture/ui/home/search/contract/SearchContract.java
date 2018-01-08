package com.inventyfy.architecture.ui.home.search.contract;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.database.table.SearchTable;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.ResponseResult;
import com.inventyfy.architecture.ui.home.search.bindingentity.ValidationCheckEntity;

import java.util.List;

public interface SearchContract {

    interface Presenter extends BasePresenter {

        LiveData<ResourcesResponse<ResponseResult>> getAllSearchResult(final String searchTerm, final String country, final String media, final String entity);

        LiveData<ValidationCheckEntity> isDataValid(final String searchTerm, final String country, final String media, final String entity);

        LiveData<ResourcesResponse<List<SearchTable>>> getAllLatestSearchResult();
    }
}
