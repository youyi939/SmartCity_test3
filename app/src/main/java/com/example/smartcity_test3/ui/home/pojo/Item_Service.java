package com.example.smartcity_test3.ui.home.pojo;

public class Item_Service {
    private int id;
    private String serviceName;
    private String imgUrl;

    public Item_Service(int id, String serviceName, String imgUrl) {
        this.id = id;
        this.serviceName = serviceName;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Item_Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
