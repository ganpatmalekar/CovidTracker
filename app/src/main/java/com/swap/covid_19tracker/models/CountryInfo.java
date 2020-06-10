package com.swap.covid_19tracker.models;

public class CountryInfo {
    private Integer id;
    private String iso2;
    private String iso3;
    private Double lat;
    private Double _long;
    private String flag;

    public Integer getId() {
        return id;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLong() {
        return _long;
    }

    public String getFlag() {
        return flag;
    }
}
