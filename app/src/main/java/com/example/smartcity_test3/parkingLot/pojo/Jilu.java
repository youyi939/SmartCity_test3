package com.example.smartcity_test3.parkingLot.pojo;

public class Jilu {
    private String entryTime;
    private String outTime;
    private String plateNumber;
    private String monetary;
    private String parkName;

    public Jilu(String entryTime, String outTime, String plateNumber, String monetary, String parkName) {
        this.entryTime = entryTime;
        this.outTime = outTime;
        this.plateNumber = plateNumber;
        this.monetary = monetary;
        this.parkName = parkName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMonetary() {
        return monetary;
    }

    public void setMonetary(String monetary) {
        this.monetary = monetary;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    @Override
    public String toString() {
        return "Jilu{" +
                "entryTime='" + entryTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", monetary='" + monetary + '\'' +
                ", parkName='" + parkName + '\'' +
                '}';
    }
}
