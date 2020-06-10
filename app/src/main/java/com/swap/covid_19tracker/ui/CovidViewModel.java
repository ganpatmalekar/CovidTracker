package com.swap.covid_19tracker.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swap.covid_19tracker.models.CountryModel;
import com.swap.covid_19tracker.repository.CovidRepository;
import com.swap.covid_19tracker.models.GlobalModel;

import java.util.List;

public class CovidViewModel extends ViewModel {

    private CovidRepository covidRepository;

    public void init(){
        covidRepository = CovidRepository.getInstance();
    }

    public LiveData<GlobalModel> getCovidRepository(){
        return covidRepository.getGlobalData();
    }

    public MutableLiveData<List<CountryModel>> getCountryRepository() {
        return covidRepository.getCountryData();
    }
}
