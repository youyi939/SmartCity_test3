package com.example.smartcity_test3.ui.newsItem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.newsItem.Detail_NewItemActivity;
import com.example.smartcity_test3.ui.newsItem.pojo.NewItem;

import java.util.List;

public class NewsItemListAdapter extends ArrayAdapter<NewItem> {

    private int resourceId;


    public NewsItemListAdapter(@NonNull Context context, int resource, @NonNull List<NewItem> objects) {
        super(context, resource, objects);
        this.resourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewItem item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        //渲染数据

        ImageView img = convertView.findViewById(R.id.news_item_img);
        TextView title = convertView.findViewById(R.id.news_item_title);
        TextView viewsNumber = convertView.findViewById(R.id.news_item_views_number);
        TextView likeNumber = convertView.findViewById(R.id.news_item_like_number);


        Glide.with(getContext()).load(item.getImgUrl()).into(img);
        title.setText(item.getTitle());
        viewsNumber.setText("点击量:"+String.valueOf(item.getViewsNumber()));
        likeNumber.setText("点赞数："+String.valueOf(item.getLikeNumber()));

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Detail_NewItemActivity.class);
                intent.putExtra("news_item",item);
                getContext().startActivity(intent);
            }
        });


        return convertView;
    }
}
