package com.inventyfy.architecture.ui.home.search.fragment;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.base.fragment.AbstractBaseDataBindingFragment;
import com.inventyfy.architecture.databinding.FragmentResultBinding;
import com.inventyfy.architecture.ui.home.search.contract.ResultContract;
import com.inventyfy.architecture.viewmodel.home.search.ResultViewModel;

public class ResultFragment extends AbstractBaseDataBindingFragment<ResultContract.Presenter, ResultViewModel, FragmentResultBinding> {

    @Override
    protected Class<ResultViewModel> getViewModel() {
        return ResultViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }
}
