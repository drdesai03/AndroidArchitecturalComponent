package com.inventyfy.architecture.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.inventyfy.architecture.database.table.ResultTable;

import java.util.List;

@Dao
public interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ResultTable> resultList);
}
