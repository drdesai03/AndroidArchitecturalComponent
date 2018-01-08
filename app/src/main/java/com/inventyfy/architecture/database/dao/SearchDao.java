package com.inventyfy.architecture.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.inventyfy.architecture.database.table.SearchTable;

import java.util.List;

@Dao
public interface SearchDao {

    @Query("SELECT * FROM search ORDER BY id DESC LIMIT 0, 5")
    LiveData<List<SearchTable>> getAllResult();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSearchQuery(SearchTable searchQuery);
}
