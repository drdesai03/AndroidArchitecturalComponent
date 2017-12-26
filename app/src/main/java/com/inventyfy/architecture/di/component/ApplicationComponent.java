package com.inventyfy.architecture.di.component;

import com.inventyfy.architecture.ArchitectureApplication;
import com.inventyfy.architecture.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(final ArchitectureApplication application);
}
