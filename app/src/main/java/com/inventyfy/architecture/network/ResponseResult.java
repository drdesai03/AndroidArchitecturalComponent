package com.inventyfy.architecture.network;

import com.google.gson.annotations.SerializedName;
import com.inventyfy.architecture.database.table.ResultTable;

import java.util.List;

public class ResponseResult {

    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private List<ResultTable> results;

    private int lastInsertedSearchId;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<ResultTable> getResults() {
        return results;
    }

    public int getLastInsertedSearchId() {
        return lastInsertedSearchId;
    }

    public ResponseResult setLastInsertedSearchId(int lastInsertedSearchId) {
        this.lastInsertedSearchId = lastInsertedSearchId;
        return this;
    }
}
