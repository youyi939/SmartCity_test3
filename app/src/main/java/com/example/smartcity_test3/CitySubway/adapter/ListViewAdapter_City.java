package com.example.smartcity_test3.CitySubway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity_test3.CitySubway.pojo.Subway;
import com.example.smartcity_test3.R;

import java.util.List;

public class ListViewAdapter_City extends ArrayAdapter<Subway> {
    private int resourceId;


    public ListViewAdapter_City(@NonNull Context context, int resource, @NonNull List<Subway> objects) {
        super(context, resource, objects);

        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }

        Subway subway = getItem(position);

        TextView lineName = convertView.findViewById(R.id.city_item_lineName);
        TextView nextName = convertView.findViewById(R.id.city_item_nextName);
        TextView reachTime = convertView.findViewById(R.id.city_item_reachTime);

        lineName.setText(subway.getLineName());
        nextName.setText(subway.getLastName());
        reachTime.setText(String.valueOf(subway.getReachTime()));

        return convertView;
    }
}











