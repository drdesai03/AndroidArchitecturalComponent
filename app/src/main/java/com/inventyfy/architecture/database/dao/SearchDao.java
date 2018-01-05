package com.inventyfy.architecture.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.inventyfy.architecture.database.table.SearchTable;

@Dao
public interface SearchDao {

    @Insert
    long insertSearchQuery(SearchTable searchQuery);
}
