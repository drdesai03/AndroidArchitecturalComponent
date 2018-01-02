package com.inventyfy.architecture.ui.home.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.databinding.FragmentSearchBinding;
import com.inventyfy.architecture.ui.home.AbstractBaseHomeFragment;
import com.inventyfy.architecture.ui.home.search.bindingentity.CountryEntity;
import com.inventyfy.architecture.ui.home.search.bindingentity.MediaEntity;
import com.inventyfy.architecture.ui.home.search.bindingentity.TypeEntity;
import com.inventyfy.architecture.ui.home.search.contract.SearchContract;
import com.inventyfy.architecture.viewmodel.home.search.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends AbstractBaseHomeFragment<SearchContract.Presenter, SearchViewModel, FragmentSearchBinding>
        implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static SearchFragment getInstance() {
        final SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    protected Class<SearchViewModel> getViewModel() {
        return SearchViewModel.class;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getUiHomeInteraction().createToolbar(false, getString(R.string.lbl_search));

        updateUi();
    }

    private void updateUi() {
        //Country Spinner
        final ArrayAdapter<CountryEntity> countryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, CountryEntity.getAllCountry());
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getBinding().spCountry.setAdapter(countryAdapter);

        //MediaType Spinner
        final ArrayAdapter<MediaEntity> mediaAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, MediaEntity.getAllMediaType());
        mediaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getBinding().spMedia.setAdapter(mediaAdapter);
        getBinding().spMedia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
                //Create Entity type spinner based on selection
                if (position > 0) {
                    createTypeEntitySpinner(MediaEntity.getAllMediaType().get(position).getMediaType());
                } else {
                    createTypeEntitySpinner("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing as there is no media type selected
            }
        });

        //EntityType Spinner
        createTypeEntitySpinner("");
        getBinding().btnSearch.setOnClickListener(this);
    }

    private void createTypeEntitySpinner(final String mediaType) {
        if (TextUtils.isEmpty(mediaType)) {
            List<String> selectMedia = new ArrayList<>();
            selectMedia.add("Please select any media type");
            final ArrayAdapter<String> entityTypeAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, selectMedia);
            entityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getBinding().spEntity.setAdapter(entityTypeAdapter);
        } else {
            final ArrayAdapter<TypeEntity> entityTypeAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, TypeEntity.getTypesFromMedia(mediaType));
            entityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getBinding().spEntity.setAdapter(entityTypeAdapter);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                final String searchQuery = getBinding().searchView.getQuery().toString();
                final String country = getBinding().spCountry.getSelectedItem().toString();
                final String media = getBinding().spMedia.getSelectedItem().toString();
                final String entity = getBinding().spEntity.getSelectedItem().toString();

                getPresenter().isDataValid(searchQuery, country, media, entity).observe(this, validationCheckEntity -> {
                    if (validationCheckEntity == null) {
                        Toast.makeText(getActivity(), "Please Enter all mandatory details", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "All details are look good.!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
