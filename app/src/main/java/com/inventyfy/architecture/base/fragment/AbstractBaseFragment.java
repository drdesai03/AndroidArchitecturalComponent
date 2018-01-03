package com.inventyfy.architecture.base.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;

import javax.inject.Inject;

public abstract class AbstractBaseFragment<P extends BasePresenter, VM extends AbstractViewModel<P>>
        extends Fragment implements InjectableFragment {

    private P presenter;
    private VM viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        presenter = viewModel.getPresenter();
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract Class<VM> getViewModel();

    protected abstract @LayoutRes
    int getLayout();
}
