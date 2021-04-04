package com.example.smartcity_test3.ui.home.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.home.pojo.Img;

import java.util.List;

public class MyAdapter extends PagerAdapter {
    private List<Img> imgList;
    private Context context;

    public MyAdapter(List<Img> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (imgList==null || imgList.size()<=0){
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lunbo,null);
        ImageView imageView = view.findViewById(R.id.img_lunbo);
        Img img = imgList.get(position % imgList.size());
        Glide.with(context).load(img.getImgUrl()).into(imageView);
        container.addView(view);
        return view;
    }
}









