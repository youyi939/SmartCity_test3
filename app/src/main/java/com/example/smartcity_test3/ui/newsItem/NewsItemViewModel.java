package com.example.smartcity_test3.ui.newsItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsItemViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewsItemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}