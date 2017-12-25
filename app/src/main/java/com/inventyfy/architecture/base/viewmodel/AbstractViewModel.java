package com.inventyfy.architecture.base.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.inventyfy.architecture.base.presenter.BasePresenter;

public abstract class AbstractViewModel<P extends BasePresenter> extends ViewModel {

    public abstract P getPresenter();
}
