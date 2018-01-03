package com.inventyfy.architecture.base.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.inventyfy.architecture.R;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class AbstractBaseNormalActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private Fragment fragmentToChange;
    private boolean isBackStack;

    public void updateToolbar(final boolean isBackNavigation, final String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackNavigation);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle(title);
    }

    public void changeFragment(final Fragment mFragment, final boolean addToBackStack) {
        this.fragmentToChange = mFragment;
        this.isBackStack = addToBackStack;
    }

    public void updateFragment(@IdRes int containerId) {
        if (this.fragmentToChange == null) {
            return;
        }
        final String backStackName = this.fragmentToChange.getClass().getSimpleName();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final boolean isPop = fragmentManager.popBackStackImmediate(backStackName, 0);
        if ((!isPop) && (fragmentManager.findFragmentByTag(backStackName) == null)) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, this.fragmentToChange, backStackName);
            if (this.isBackStack) {
                fragmentTransaction.addToBackStack(backStackName);
            }
            fragmentTransaction.commit();
        }
        this.fragmentToChange = null;
        this.isBackStack = false;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
