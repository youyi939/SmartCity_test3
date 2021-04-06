package com.example.smartcity_test3.ui.personal.pojo;

public class Order {
    private String orderNum;
    private String createTime;


    public Order(String orderNum, String createTime) {
        this.orderNum = orderNum;
        this.createTime = createTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNum='" + orderNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}

