package com.inventyfy.architecture.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.inventyfy.architecture.ArchitectureApplication;
import com.inventyfy.architecture.base.viewmodel.BaseViewModelFactory;
import com.inventyfy.architecture.database.AppDatabase;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.network.support.LiveDataCallAdapterFactory;
import com.inventyfy.architecture.repository.home.search.SearchRepository;
import com.inventyfy.architecture.repository.home.search.SearchRepositoryImpl;
import com.inventyfy.architecture.viewmodel.home.search.SearchViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private static final String DB_NAME = "itunes.db";

    @Provides
    @Singleton
    SearchService provideGithubApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(SearchService.class);
    }

    @Singleton
    @Provides
    AppDatabase getDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, DB_NAME).build();
    }


    @Provides
    BaseViewModelFactory provideBaseViewModelFactory() {
        return new BaseViewModelFactory();
    }

//    @Provides
//    ViewModelProvider.Factory provideListIssuesViewModelFactory(BaseViewModelFactory viewModelFactory) {
//        return viewModelFactory;
//    }
}
