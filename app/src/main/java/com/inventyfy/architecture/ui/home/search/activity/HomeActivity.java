package com.inventyfy.architecture.ui.home.search.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.databinding.ActivityHomeBinding;
import com.inventyfy.architecture.ui.home.AbstractBaseHomeActivity;
import com.inventyfy.architecture.ui.home.search.fragment.SearchFragment;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AbstractBaseHomeActivity implements AbstractBaseHomeActivity.UiHomeInteraction, HasSupportFragmentInjector {

    private ActivityHomeBinding homeBinding;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        loadSearchFragment();
    }

    private void loadSearchFragment() {
        SearchFragment searchFragment = SearchFragment.getInstance();
        changeFragment(searchFragment, true);
        updateFragment(R.id.content_panel);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void createToolbar(boolean isBackNavigation, String title) {
        updateToolbar(isBackNavigation, title);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
