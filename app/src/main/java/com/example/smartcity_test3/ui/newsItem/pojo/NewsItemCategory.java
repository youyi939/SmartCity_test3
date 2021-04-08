package com.example.smartcity_test3.ui.newsItem.pojo;

import java.io.Serializable;
import java.util.List;

public class NewsItemCategory implements Serializable {
    private int dictCode;
    private String dictLabel;
    private List<NewItem> newItems;

    @Override
    public String toString() {
        return "NewsItemCategory{" +
                "dictCode=" + dictCode +
                ", dictLabel='" + dictLabel + '\'' +
                ", newItems=" + newItems +
                '}';
    }

    public int getDictCode() {
        return dictCode;
    }

    public void setDictCode(int dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public List<NewItem> getNewItems() {
        return newItems;
    }

    public void setNewItems(List<NewItem> newItems) {
        this.newItems = newItems;
    }

    public NewsItemCategory(int dictCode, String dictLabel, List<NewItem> newItems) {
        this.dictCode = dictCode;
        this.dictLabel = dictLabel;
        this.newItems = newItems;
    }
}
