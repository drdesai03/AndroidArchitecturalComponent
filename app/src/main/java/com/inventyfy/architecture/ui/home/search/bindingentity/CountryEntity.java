package com.inventyfy.architecture.ui.home.search.bindingentity;

import java.util.ArrayList;
import java.util.List;

public class CountryEntity {

    private String countryCode;
    private String countryName;

    public CountryEntity(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return countryName;
    }

    public static List<CountryEntity> getAllCountry() {
        final List<CountryEntity> countryList = new ArrayList<>();
        countryList.add(new CountryEntity("all", "All"));
        countryList.add(new CountryEntity("IN", "India"));
        countryList.add(new CountryEntity("US", "United States"));
        countryList.add(new CountryEntity("UK", "United Kingdom"));
        return countryList;
    }
}
