package com.example.smartcity_test3.ui.newsItem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.newsItem.pojo.NewItem;

import java.util.List;

public class RecommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewItem> lists;
    private RecyclerView.ViewHolder holder;

    public RecommentAdapter(List<NewItem> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomment_layout,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        NewItem item = lists.get(position);

        //渲染数据
        ImageView imageView = holder.itemView.findViewById(R.id.recommend_img);
        TextView name = holder.itemView.findViewById(R.id.recommend_name);
        TextView viewsNum = holder.itemView.findViewById(R.id.recommend_viewsNumber);

        Glide.with(holder.itemView).load(item.getImgUrl()).into(imageView);
        name.setText(item.getTitle());
        viewsNum.setText(String.valueOf(item.getViewsNumber()));


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
