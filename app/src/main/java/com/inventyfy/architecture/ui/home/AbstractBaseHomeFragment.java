package com.inventyfy.architecture.ui.home;

import android.app.Activity;
import android.databinding.ViewDataBinding;

import com.inventyfy.architecture.base.fragment.AbstractBaseDataBindingFragment;
import com.inventyfy.architecture.base.presenter.BasePresenter;
import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;

public abstract class AbstractBaseHomeFragment<P extends BasePresenter, VM extends AbstractViewModel<P>, T extends ViewDataBinding>
        extends AbstractBaseDataBindingFragment<P, VM, T> {

    private AbstractBaseHomeActivity.UiHomeInteraction uiHomeInteraction;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            uiHomeInteraction = (AbstractBaseHomeActivity.UiHomeInteraction) activity;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public AbstractBaseHomeActivity.UiHomeInteraction getUiHomeInteraction() {
        if (uiHomeInteraction == null) {
            throw new NullPointerException("Please extends UiHomeInteraction interface to your calling activity");
        }
        return uiHomeInteraction;
    }
}
