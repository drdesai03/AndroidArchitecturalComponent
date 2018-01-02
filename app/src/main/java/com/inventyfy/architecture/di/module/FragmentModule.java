package com.inventyfy.architecture.di.module;

import com.inventyfy.architecture.ui.home.search.fragment.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    public abstract SearchFragment contributeSearchFragment();

}
