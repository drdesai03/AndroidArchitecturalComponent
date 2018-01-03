package com.inventyfy.architecture.viewmodel.home.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;

import com.inventyfy.architecture.base.viewmodel.AbstractViewModel;
import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.helper.ResourcesResponse;
import com.inventyfy.architecture.repository.home.search.SearchRepository;
import com.inventyfy.architecture.repository.home.search.SearchRepositoryImpl;
import com.inventyfy.architecture.ui.home.search.bindingentity.ValidationCheckEntity;
import com.inventyfy.architecture.ui.home.search.contract.SearchContract;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends AbstractViewModel<SearchContract.Presenter> implements SearchContract.Presenter {

    private LiveData<ResourcesResponse<List<ResultTable>>> resultResponse;
    private LiveData<ValidationCheckEntity> validation;
    private MutableLiveData<ValidationCheckEntity> validationCheck = new MutableLiveData<>();

    private SearchRepository searchRepository;

    @Inject
    SearchViewModel(SearchRepositoryImpl searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public SearchContract.Presenter getPresenter() {
        return this;
    }

    @Override
    public LiveData<ResourcesResponse<List<ResultTable>>> getAllSearchResult() {
        return searchRepository.getSearchResult("Yelp", null, null, null);
    }

    @Override
    public LiveData<ValidationCheckEntity> isDataValid(String searchTerm, String country, String media, String entity) {
        checkDetails(searchTerm, country, media, entity);
        return validation;
    }

    private void checkDetails(final String searchTerm, final String country, final String media, final String entity) {
        if (TextUtils.isEmpty(searchTerm)) {
            validationCheck.setValue(null);
        } else {
            validationCheck.setValue(new ValidationCheckEntity());
        }
        validation = Transformations.switchMap(validationCheck, input -> {
            MediatorLiveData<ValidationCheckEntity> localObserver = new MediatorLiveData<>();
            if (input == null) {
                localObserver.setValue(null);
            } else {
                localObserver.setValue(new ValidationCheckEntity());
            }
            return localObserver;
        });
    }

    private void getDataFromServer(final String searchTerm) {
    }
}
