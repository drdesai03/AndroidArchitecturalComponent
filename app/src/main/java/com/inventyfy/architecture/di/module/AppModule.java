package com.inventyfy.architecture.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.inventyfy.architecture.database.AppDatabase;
import com.inventyfy.architecture.database.dao.ResultDao;
import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.di.common.ApplicationScope;
import com.inventyfy.architecture.helper.AppExecutors;
import com.inventyfy.architecture.network.SearchService;
import com.inventyfy.architecture.network.support.LiveDataCallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private static final String DB_NAME = "itunes.db";

    @ApplicationScope
    @Provides
    SearchService provideSearchService() {
        return new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(SearchService.class);
    }

    @ApplicationScope
    @Provides
    AppDatabase getDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration()
                .build();
    }

    @ApplicationScope
    @Provides
    AppExecutors provideAppExecutor() {
        return new AppExecutors();
    }

    @ApplicationScope
    @Provides
    SearchDao provideSearchDao(AppDatabase appDatabase) {
        return appDatabase.getSearchDao();
    }

    @ApplicationScope
    @Provides
    ResultDao provideResultDao(AppDatabase appDatabase) {
        return appDatabase.getResultDao();
    }
}
