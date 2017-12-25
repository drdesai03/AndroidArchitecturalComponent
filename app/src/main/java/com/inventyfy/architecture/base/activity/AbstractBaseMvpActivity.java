package com.inventyfy.architecture.base.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;

public abstract class AbstractBaseMvpActivity<P extends BasePresenter, VM extends AbstractViewModel<P>>
        extends AbstractBaseNormalActivity {

    private P presenter;
    private VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(getViewModel());

        presenter = viewModel.getPresenter();
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract Class<VM> getViewModel();
}
