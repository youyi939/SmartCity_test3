package com.example.smartcity_test3.ui.newsItem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.newsItem.pojo.NewItem;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<NewItem> newItems;

    public ViewPagerAdapter(Context context, List<NewItem> newItems) {
        this.context = context;
        this.newItems = newItems;
    }

    @Override
    public int getCount() {
        if (newItems==null||newItems.size()<=0){
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_img_layout,null);
        ImageView imageView = view.findViewById(R.id.news_item_image_layout);
        Glide.with(view).load(newItems.get(position%newItems.size()).getImgUrl()).into(imageView);
//        Log.i("TAG1", "instantiateItem: "+newItems.get(position%newItems.size()).getImgUrl());
        container.addView(view);
        return view;
    }
}
