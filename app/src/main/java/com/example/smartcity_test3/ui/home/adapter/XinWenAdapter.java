package com.example.smartcity_test3.ui.home.adapter;

import android.content.Context;
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
import com.example.smartcity_test3.ui.home.pojo.ItemData;

import java.util.List;



public class XinWenAdapter extends ArrayAdapter<ItemData> {

    private int resourceId;

    public XinWenAdapter(@NonNull Context context, int resource, @NonNull List<ItemData> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        ItemData itemData =getItem(position);
        ImageView imageView = convertView.findViewById(R.id.img_xinwen);
        TextView title = convertView.findViewById(R.id.title_xinwen);
        TextView context = convertView.findViewById(R.id.content_xinwen);
        TextView num = convertView.findViewById(R.id.num_xinwen);
        TextView time = convertView.findViewById(R.id.time_xinwen);

        Glide.with(getContext()).load(itemData.getImgUrl()).into(imageView);
        time.setText(itemData.getCreateTime());
        title.setText(itemData.getTitle());
        num.setText(String.valueOf(itemData.getViewsNumber()));
        context.setText(itemData.getContent());

        return convertView;
    }
}
