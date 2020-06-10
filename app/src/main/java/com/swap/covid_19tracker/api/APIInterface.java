package com.swap.covid_19tracker.api;

import com.swap.covid_19tracker.models.CountryModel;
import com.swap.covid_19tracker.models.GlobalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("all")
    Call<GlobalModel> getGlobalData();

    @GET("countries")
    Call<List<CountryModel>> getCountryData();
}
