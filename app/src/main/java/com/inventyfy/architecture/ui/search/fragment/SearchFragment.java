package com.inventyfy.architecture.ui.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.databinding.FragmentSearchBinding;
import com.inventyfy.architecture.ui.search.AbstractBaseHomeFragment;
import com.inventyfy.architecture.ui.search.contract.SearchContract;
import com.inventyfy.architecture.ui.search.viewmodel.SearchViewModel;

public class SearchFragment extends AbstractBaseHomeFragment<SearchContract.Presenter, SearchViewModel, FragmentSearchBinding> {

    public static SearchFragment getInstance() {
        final SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    protected Class<SearchViewModel> getViewModel() {
        return SearchViewModel.class;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getUiHomeInteraction().createToolbar(false, getString(R.string.lbl_search));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }
}
