package com.example.smartcity_test3.CitySubway.pojo;

public class Info{
    private String lineName;
    private String remainingTime;
    private String stationsNumber;
    private int km;
    private String runStationsName;

    public Info(String lineName, String remainingTime, String stationsNumber, int km, String runStationsName) {
        this.lineName = lineName;
        this.remainingTime = remainingTime;
        this.stationsNumber = stationsNumber;
        this.km = km;
        this.runStationsName = runStationsName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getStationsNumber() {
        return stationsNumber;
    }

    public void setStationsNumber(String stationsNumber) {
        this.stationsNumber = stationsNumber;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getRunStationsName() {
        return runStationsName;
    }

    public void setRunStationsName(String runStationsName) {
        this.runStationsName = runStationsName;
    }
}