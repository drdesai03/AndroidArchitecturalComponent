package com.inventyfy.architecture.di.module;

import com.inventyfy.architecture.ui.home.search.activity.HomeActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    public abstract HomeActivity contributeHomeActivity();
}
