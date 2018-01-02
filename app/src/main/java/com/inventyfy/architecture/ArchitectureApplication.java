package com.inventyfy.architecture;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.inventyfy.architecture.di.AppInjector;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class ArchitectureApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        AppInjector.initialize(this);
        Log.d("Tag", "Value : " + dispatchingAndroidInjector);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
