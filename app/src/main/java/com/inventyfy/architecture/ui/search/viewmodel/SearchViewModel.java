package com.inventyfy.architecture.ui.search.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.ui.search.contract.SearchContract;

public class SearchViewModel extends AbstractViewModel<SearchContract.Presenter> implements SearchContract.Presenter {

    @Override
    public SearchContract.Presenter getPresenter() {
        return this;
    }
}
