package com.inventyfy.architecture;

import android.app.Application;

import com.inventyfy.architecture.di.component.ApplicationComponent;
import com.inventyfy.architecture.di.component.DaggerApplicationComponent;

public class ArchitectureApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().build();
        component.inject(this);
    }
}
