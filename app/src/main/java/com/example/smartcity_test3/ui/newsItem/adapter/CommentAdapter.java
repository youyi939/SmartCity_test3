package com.example.smartcity_test3.ui.newsItem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.smartcity_test3.R;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<String> {

    private int resourceId;


    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<String> comments) {
        super(context, resource, comments);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String comment = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }

        TextView content = convertView.findViewById(R.id.comment_item);
        content.setText(comment);

        //渲染数据
        return convertView;
    }
}
