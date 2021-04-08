package com.example.smartcity_test3.ui.newsItem.adapter;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.newsItem.pojo.NewsItemCategory;

import java.util.List;

public class NewsItemLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsItemCategory> lists;
    private RecyclerView.ViewHolder holder;
    private Handler handler;

    public NewsItemLabelAdapter(List<NewsItemCategory> lists, Handler handler) {
        this.lists = lists;
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_category_label,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        NewsItemCategory newsItemCategory = lists.get(position);

        //渲染数据
        TextView label = holder.itemView.findViewById(R.id.news_item_label);
        label.setText(newsItemCategory.getDictLabel());

        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.obj = position;
                message.what = 2;
                handler.sendMessage(message);
            }
        });


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
