package com.example.smartcity_test3.ui.home.pojo;

import java.util.List;

public class Item {
    private String dictLabel;
    private int code;
    private List<ItemData> dataList;

    public Item(String dictLabel, int code, List<ItemData> dataList) {
        this.dictLabel = dictLabel;
        this.code = code;
        this.dataList = dataList;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ItemData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ItemData> dataList) {
        this.dataList = dataList;
    }
}
