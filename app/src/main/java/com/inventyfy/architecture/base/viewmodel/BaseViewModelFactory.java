package com.inventyfy.architecture.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.inventyfy.architecture.repository.home.search.SearchRepository;
import com.inventyfy.architecture.viewmodel.home.search.SearchViewModel;

import javax.inject.Inject;

//This class is only used when there is no parameter constructor available in viewModelClass.
public class BaseViewModelFactory implements ViewModelProvider.Factory {

    private SearchViewModel mViewModel;

    SearchRepository searchRepository;

    public BaseViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) mViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
