package com.inventyfy.architecture.base.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;

public abstract class AbstractBaseFragment<P extends BasePresenter, VM extends AbstractViewModel<P>>
        extends Fragment {

    private P presenter;
    private VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(getViewModel());
        presenter = viewModel.getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract Class<VM> getViewModel();

    protected abstract @LayoutRes
    int getLayout();
}
