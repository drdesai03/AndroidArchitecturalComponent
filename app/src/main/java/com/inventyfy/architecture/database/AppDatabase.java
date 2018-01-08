package com.inventyfy.architecture.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.inventyfy.architecture.database.dao.ResultDao;
import com.inventyfy.architecture.database.dao.SearchDao;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.database.table.SearchTable;

import javax.inject.Inject;

@Database(entities = {SearchTable.class, ResultTable.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SearchDao getSearchDao();

    public abstract ResultDao getResultDao();
}
