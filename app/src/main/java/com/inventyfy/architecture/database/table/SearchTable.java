package com.inventyfy.architecture.database.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "search", indices = {@Index(value = "id"), @Index(value = "searchText")})
public class SearchTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String searchText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
