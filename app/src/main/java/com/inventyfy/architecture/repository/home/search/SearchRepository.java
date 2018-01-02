package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;

public interface SearchRepository {

    LiveData<ResourcesResponse<ResultTable>> getSearchResult(final String searchQuery, final String country,
                                                             final String media, final String entity);
}
