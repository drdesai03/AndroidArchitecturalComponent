package com.inventyfy.architecture.viewmodel.home.search;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.ui.home.search.contract.ResultContract;

public class ResultViewModel extends AbstractViewModel<ResultContract.Presenter> implements ResultContract.Presenter {

    @Override
    public ResultContract.Presenter getPresenter() {
        return this;
    }
}
