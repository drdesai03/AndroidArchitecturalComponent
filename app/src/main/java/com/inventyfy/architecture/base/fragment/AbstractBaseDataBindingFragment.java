package com.inventyfy.architecture.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;

public abstract class AbstractBaseDataBindingFragment<P extends BasePresenter, VM extends AbstractViewModel<P>, T extends ViewDataBinding>
        extends AbstractBaseFragment<P, VM> {

    private T binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return binding.getRoot();
    }

    public T getBinding() {
        if (binding == null) {
            throw new ExceptionInInitializerError("Please try this method after onCreateView() lifecycle call");
        }
        return binding;
    }
}
