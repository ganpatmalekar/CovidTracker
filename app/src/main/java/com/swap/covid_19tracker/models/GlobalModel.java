package com.swap.covid_19tracker.models;

public class GlobalModel {

    private long updated;
    private Integer cases;
    private Integer deaths;
    private Integer recovered;
    private Integer critical;

    public long getUpdated() {
        return updated;
    }

    public Integer getCases() {
        return cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public Integer getCritical() {
        return critical;
    }
}
