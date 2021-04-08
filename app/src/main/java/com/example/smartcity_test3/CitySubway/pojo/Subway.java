package com.example.smartcity_test3.CitySubway.pojo;

public class Subway {
    private String lineName;
    private String lastName;
    private int lineId;
    private int reachTime;

    public Subway(String lineName, String lastName, int lineId, int reachTime) {
        this.lineName = lineName;
        this.lastName = lastName;
        this.lineId = lineId;
        this.reachTime = reachTime;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }

    @Override
    public String toString() {
        return "Subway{" +
                "lineName='" + lineName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lineId=" + lineId +
                ", reachTime=" + reachTime +
                '}';
    }
}
