package com.inventyfy.architecture.di.component;

import android.app.Application;

import com.inventyfy.architecture.ArchitectureApplication;
import com.inventyfy.architecture.di.common.ApplicationScope;
import com.inventyfy.architecture.di.module.ActivityModule;
import com.inventyfy.architecture.di.module.AppModule;
import com.inventyfy.architecture.di.module.FragmentModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(ArchitectureApplication application);
}
