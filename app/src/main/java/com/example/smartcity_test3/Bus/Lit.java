package com.example.smartcity_test3.Bus;

import java.io.Serializable;

public class Lit implements Serializable {
    private  String linesId;
    private String stepsId;
    private String names;
    private String sequence;

    public Lit(String linesId, String stepsId, String names, String sequence) {
        this.linesId = linesId;
        this.stepsId = stepsId;
        this.names = names;
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "Lit{" +
                "linesId='" + linesId + '\'' +
                ", stepsId='" + stepsId + '\'' +
                ", names='" + names + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }

    public Lit() {
    }

    public String getLinesId() {
        return linesId;
    }

    public void setLinesId(String linesId) {
        this.linesId = linesId;
    }

    public String getStepsId() {
        return stepsId;
    }

    public void setStepsId(String stepsId) {
        this.stepsId = stepsId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
