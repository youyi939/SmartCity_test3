package com.example.smartcity_test3.traffic.pojo;

import java.io.Serializable;

public class Traffic implements Serializable {
    private int id ;
    private String licencePlate ;            //车牌号
    private String disposeState;             //处理状态
    private String badTime;                  //违法时间
    private String money ;                  //罚金
    private String deductMarks;            //扣分
    private String illegalSites;            //违法地址
    private String noticeNo ;               //违章号
    private String engineNumber;            //发动机号
    private String catType ;                //车型
    private String trafficOffence ;         //违法信息


    public Traffic(int id, String licencePlate, String disposeState, String badTime, String money, String deductMarks, String illegalSites, String noticeNo, String engineNumber, String catType, String trafficOffence) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.disposeState = disposeState;
        this.badTime = badTime;
        this.money = money;
        this.deductMarks = deductMarks;
        this.illegalSites = illegalSites;
        this.noticeNo = noticeNo;
        this.engineNumber = engineNumber;
        this.catType = catType;
        this.trafficOffence = trafficOffence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getDisposeState() {
        return disposeState;
    }

    public void setDisposeState(String disposeState) {
        this.disposeState = disposeState;
    }

    public String getBadTime() {
        return badTime;
    }

    public void setBadTime(String badTime) {
        this.badTime = badTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDeductMarks() {
        return deductMarks;
    }

    public void setDeductMarks(String deductMarks) {
        this.deductMarks = deductMarks;
    }

    public String getIllegalSites() {
        return illegalSites;
    }

    public void setIllegalSites(String illegalSites) {
        this.illegalSites = illegalSites;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public String getTrafficOffence() {
        return trafficOffence;
    }

    public void setTrafficOffence(String trafficOffence) {
        this.trafficOffence = trafficOffence;
    }

    @Override
    public String toString() {
        return "Traffic{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", disposeState='" + disposeState + '\'' +
                ", badTime='" + badTime + '\'' +
                ", money='" + money + '\'' +
                ", deductMarks='" + deductMarks + '\'' +
                ", illegalSites='" + illegalSites + '\'' +
                ", noticeNo='" + noticeNo + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", catType='" + catType + '\'' +
                ", trafficOffence='" + trafficOffence + '\'' +
                '}';
    }
}
