package com.example.smartcity_test3.ui.newsItem.pojo;

import java.io.Serializable;

public class NewItem implements Serializable {

    private String updateTime;
    private int id;
    private String title;
    private String content;
    private String imgUrl;
    private String pressCategory;
    private int isRecommend;
    private int likeNumber;
    private int viewsNumber;

    @Override
    public String toString() {
        return "NewItem{" +
                "updateTime='" + updateTime + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", pressCategory='" + pressCategory + '\'' +
                ", isRecommend=" + isRecommend +
                ", likeNumber=" + likeNumber +
                ", viewsNumber=" + viewsNumber +
                '}';
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPressCategory() {
        return pressCategory;
    }

    public void setPressCategory(String pressCategory) {
        this.pressCategory = pressCategory;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public NewItem(String updateTime, int id, String title, String content, String imgUrl, String pressCategory, int isRecommend, int likeNumber, int viewsNumber) {
        this.updateTime = updateTime;
        this.id = id;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.pressCategory = pressCategory;
        this.isRecommend = isRecommend;
        this.likeNumber = likeNumber;
        this.viewsNumber = viewsNumber;
    }
}
