package com.example.smartcity_test3.Bus;

import java.io.Serializable;
import java.util.List;

public class Big implements Serializable {
    private  String endTime;
    private  String id;
    private  String name;
    private String first;
    private String end;
    private String startTime;
    private  String  price;
    private  String mileage;
    private List<Lit>lits;

    public Big() {
    }

    @Override
    public String toString() {
        return "Big{" +
                "endTime='" + endTime + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", first='" + first + '\'' +
                ", end='" + end + '\'' +
                ", startTime='" + startTime + '\'' +
                ", price='" + price + '\'' +
                ", mileage='" + mileage + '\'' +
                ", lits=" + lits +
                '}';
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public List<Lit> getLits() {
        return lits;
    }

    public void setLits(List<Lit> lits) {
        this.lits = lits;
    }

    public Big(String endTime, String id, String name, String first, String end, String startTime, String price, String mileage, List<Lit> lits) {
        this.endTime = endTime;
        this.id = id;
        this.name = name;
        this.first = first;
        this.end = end;
        this.startTime = startTime;
        this.price = price;
        this.mileage = mileage;
        this.lits = lits;
    }
}
