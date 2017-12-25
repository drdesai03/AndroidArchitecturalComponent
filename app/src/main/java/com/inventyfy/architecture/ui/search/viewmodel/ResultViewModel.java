package com.inventyfy.architecture.ui.search.viewmodel;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.ui.search.contract.ResultContract;

public class ResultViewModel extends AbstractViewModel<ResultContract.Presenter> implements ResultContract.Presenter {

    @Override
    public ResultContract.Presenter getPresenter() {
        return this;
    }
}
