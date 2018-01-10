package com.inventyfy.architecture.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.inventyfy.architecture.database.table.ResultTable;

import java.util.List;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM result WHERE searchId = :searchId")
    LiveData<List<ResultTable>> getAllResult(int searchId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ResultTable> resultList);
}
