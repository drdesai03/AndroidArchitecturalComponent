package com.inventyfy.architecture.network;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.network.support.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface SearchService {

    @GET("search")
    LiveData<ApiResponse<ResultTable>> getSearchResult(@Query("term") final String term);
}
