package com.inventyfy.architecture.repository.home.search;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;

import java.util.List;

public interface SearchRepository {

    LiveData<ResourcesResponse<List<ResultTable>>> getSearchResult(final String searchQuery, final String country,
                                                                  final String media, final String entity);
}
