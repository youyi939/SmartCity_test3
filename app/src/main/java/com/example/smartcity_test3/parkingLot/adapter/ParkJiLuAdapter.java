package com.example.smartcity_test3.parkingLot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.pojo.Jilu;

import java.util.List;



public class ParkJiLuAdapter extends ArrayAdapter<Jilu> {

    private int resourceId;

    public ParkJiLuAdapter(@NonNull Context context, int resource, @NonNull List<Jilu> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        Jilu jilu = getItem(position);
        TextView in = convertView.findViewById(R.id.in_jilu);
        TextView out = convertView.findViewById(R.id.out_jilu);
        TextView name = convertView.findViewById(R.id.name_jilu);
        TextView money = convertView.findViewById(R.id.money_jilu);
        TextView num = convertView.findViewById(R.id.num_jilu);

        in.setText("入场时间："+jilu.getEntryTime());
        out.setText("出场时间："+jilu.getOutTime());
        name.setText("名称："+jilu.getParkName());
        num.setText("车牌号："+jilu.getPlateNumber());
        money.setText("金额："+jilu.getMonetary());




        return convertView;
    }
}