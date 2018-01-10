package com.inventyfy.architecture.ui.home.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.base.fragment.AbstractBaseDataBindingFragment;
import com.inventyfy.architecture.databinding.FragmentResultBinding;
import com.inventyfy.architecture.ui.home.AbstractBaseHomeFragment;
import com.inventyfy.architecture.ui.home.search.adapter.ResultAdapter;
import com.inventyfy.architecture.ui.home.search.contract.ResultContract;
import com.inventyfy.architecture.viewmodel.home.result.ResultViewModel;

public class ResultFragment extends AbstractBaseHomeFragment<ResultContract.Presenter, ResultViewModel, FragmentResultBinding> {

    private static final String SEARCH_ITEM_ID = "search_item_id";

    private ResultAdapter adapter;

    public static ResultFragment getInstance(final int searchItemId) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(SEARCH_ITEM_ID, searchItemId);
        resultFragment.setArguments(arguments);
        return resultFragment;
    }

    @Override
    protected Class<ResultViewModel> getViewModel() {
        return ResultViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getUiHomeInteraction().createToolbar(true, getString(R.string.lbl_result));
        int searchId = getArguments().getInt(SEARCH_ITEM_ID);

        getPresenter().getAllResultFromSearchId(searchId).observe(this, listResourcesResponse -> {
            if (listResourcesResponse == null) {
                Toast.makeText(getActivity(), "Result Not Found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Result Found", Toast.LENGTH_SHORT).show();
                adapter = new ResultAdapter(listResourcesResponse);
                getBinding().rvResult.setLayoutManager(new LinearLayoutManager(getActivity()));
                getBinding().rvResult.setAdapter(adapter);
            }
        });
    }
}
