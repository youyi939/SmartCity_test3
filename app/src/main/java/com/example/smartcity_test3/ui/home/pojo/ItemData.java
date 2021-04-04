package com.example.smartcity_test3.ui.home.pojo;

public class ItemData {
    private String createTime;
    private String imgUrl;
    private String title;
    private String content;
    private int viewsNumber;

    public ItemData(String createTime, String imgUrl, String title, String content, int viewsNumber) {
        this.createTime = createTime;
        this.imgUrl = imgUrl;
        this.title = title;
        this.content = content;
        this.viewsNumber = viewsNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }
}
