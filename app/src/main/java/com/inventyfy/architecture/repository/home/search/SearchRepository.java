package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.database.table.SearchTable;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.network.ResponseResult;

import java.util.List;

public interface SearchRepository {

    LiveData<ResourcesResponse<ResponseResult>> getSearchResult(final String searchQuery, final String country,
                                                                final String media, final String entity);

    LiveData<ResourcesResponse<List<SearchTable>>> getLastSearchFromDb();
}
