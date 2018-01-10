package com.inventyfy.architecture.ui.home.search.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.base.activity.ToolbarListener;
import com.inventyfy.architecture.databinding.ActivityHomeBinding;
import com.inventyfy.architecture.ui.home.AbstractBaseHomeActivity;
import com.inventyfy.architecture.ui.home.search.fragment.SearchFragment;

public class HomeActivity extends AbstractBaseHomeActivity implements AbstractBaseHomeActivity.UiHomeInteraction {

    private ActivityHomeBinding homeBinding;

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
    public void createToolbarWithCallback(boolean isBackNavigation, String title, ToolbarListener toolbarListener) {
        updateToolbar(isBackNavigation, title, toolbarListener);
    }

    @Override
    public void updateFragment(Fragment fragment, boolean addToBackStack) {
        changeFragment(fragment, addToBackStack);
        updateFragment(R.id.content_panel);

    }
}
