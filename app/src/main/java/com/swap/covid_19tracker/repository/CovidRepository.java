package com.swap.covid_19tracker.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.swap.covid_19tracker.api.APIClient;
import com.swap.covid_19tracker.api.APIInterface;
import com.swap.covid_19tracker.models.CountryModel;
import com.swap.covid_19tracker.models.GlobalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidRepository {
    private static CovidRepository covidRepository;
    private APIInterface apiInterface;


    public CovidRepository() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public static CovidRepository getInstance() {
        if (covidRepository == null) {
            covidRepository = new CovidRepository();
        }
        return covidRepository;
    }

    public MutableLiveData<GlobalModel> getGlobalData() {
        MutableLiveData<GlobalModel> globalModelMutableLiveData = new MutableLiveData<>();

        apiInterface.getGlobalData().enqueue(new Callback<GlobalModel>() {
            @Override
            public void onResponse(Call<GlobalModel> call, Response<GlobalModel> response) {
                if (response.isSuccessful()) {
                    globalModelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GlobalModel> call, Throwable t) {
                globalModelMutableLiveData.setValue(null);

            }
        });

        return globalModelMutableLiveData;
    }

    public MutableLiveData<List<CountryModel>> getCountryData() {
        MutableLiveData<List<CountryModel>> countryModelMutableLiveData = new MutableLiveData<>();

        apiInterface.getCountryData().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()) {
                    countryModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                countryModelMutableLiveData.setValue(null);
                Log.d("CovidRepository", "Error: " + t.getMessage());
            }
        });

        return countryModelMutableLiveData;
    }
}
