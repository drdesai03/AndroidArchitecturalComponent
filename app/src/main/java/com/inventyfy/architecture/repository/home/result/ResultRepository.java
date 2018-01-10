package com.inventyfy.architecture.repository.home.result;

import android.arch.lifecycle.LiveData;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;

import java.util.List;

public interface ResultRepository {

    LiveData<ResourcesResponse<List<ResultTable>>> getAllResultFromSearchId(final int searchId);
}
