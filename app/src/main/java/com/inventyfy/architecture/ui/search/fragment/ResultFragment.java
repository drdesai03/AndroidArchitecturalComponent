package com.inventyfy.architecture.ui.search.fragment;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.base.fragment.AbstractBaseDataBindingFragment;
import com.inventyfy.architecture.databinding.FragmentResultBinding;
import com.inventyfy.architecture.ui.search.contract.ResultContract;
import com.inventyfy.architecture.ui.search.viewmodel.ResultViewModel;

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
