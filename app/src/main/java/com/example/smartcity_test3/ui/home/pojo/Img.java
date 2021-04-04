package com.example.smartcity_test3.ui.home.pojo;

public class Img {
    private int sort;
    private String imgUrl;

    public Img(int sort, String imgUrl) {
        this.sort = sort;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Img{" +
                "sort=" + sort +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
