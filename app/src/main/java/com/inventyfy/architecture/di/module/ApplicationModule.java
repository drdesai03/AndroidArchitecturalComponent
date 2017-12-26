package com.inventyfy.architecture.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.inventyfy.architecture.database.AppDatabase;
import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.network.support.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private static final String DB_NAME = "appDatabase.db";

    @Provides
    SearchService provideRetroFitService() {
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

    @Singleton
    @Provides
    SearchDao getSearchDao(AppDatabase appDatabase) {
        return appDatabase.getSearchDao();
    }
}
