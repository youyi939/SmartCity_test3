package com.example.smartcity_test3.ui.newsItem.pojo;

import java.io.Serializable;
import java.util.List;

public class Comments implements Serializable {
    private String total;
    private List<String> strings;

    public Comments(String total, List<String> strings) {
        this.total = total;
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "total='" + total + '\'' +
                ", strings=" + strings +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}
