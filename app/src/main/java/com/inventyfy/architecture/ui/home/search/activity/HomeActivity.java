package com.inventyfy.architecture.ui.home.search.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.inventyfy.architecture.R;
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
        changeFragment(SearchFragment.getInstance(), true);
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
}
