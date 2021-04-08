package com.example.smartcity_test3.CitySubway.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcity_test3.CitySubway.pojo.SubData;
import com.example.smartcity_test3.R;

import java.util.List;

public class TimeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SubData> infoList;
    private RecyclerView.ViewHolder holder;
    private String runStation;


    public TimeRecyclerAdapter(List<SubData> infos,String run) {
        this.infoList = infos;
        this.runStation = run;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_layout,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SubData subData = infoList.get(position);
        TextView name = holder.itemView.findViewById(R.id.station_name);
        ImageView imageView = holder.itemView.findViewById(R.id.time_img);
        name.setText(subData.getName());

        if (runStation.equals(subData.getName())){
            imageView.setImageResource(R.drawable.ic_launcher_background);
            Log.i("Ken", "onBindViewHolder: "+subData.toString()+position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }
}
