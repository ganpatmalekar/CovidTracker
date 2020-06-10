package com.swap.covid_19tracker.models;

public class CountryModel {
    private long updated;
    private String country;
    private CountryInfo countryInfo;
    private long cases;
    private long recovered;
    private long critical;
    private long active;
    private long deaths;
    private long todayCases;
    private long todayDeaths;

    public String getCountry() {
        return country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public long getCases() {
        return cases;
    }

    public long getUpdated() {
        return updated;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getCritical() {
        return critical;
    }

    public long getActive() {
        return active;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }
}
