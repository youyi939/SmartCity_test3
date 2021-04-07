package com.example.smartcity_test3.ui.home.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.CitySubway.CItySubWayActivity;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.ParkingActivity;
import com.example.smartcity_test3.ui.home.pojo.Item_Service;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item_Service> list;
    private RecyclerView.ViewHolder holder;

    public ServiceAdapter(List<Item_Service> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item_Service test = list.get(position);
        TextView name = holder.itemView.findViewById(R.id.name_service);
        ImageView imageView = holder.itemView.findViewById(R.id.img_service);
        name.setText(test.getServiceName());

        if (test.getServiceName().equals("更多服务")){
            Glide.with(holder.itemView).load(R.drawable.ic_launcher_foreground).into(imageView);
        }else {
            Glide.with(holder.itemView).load(test.getImgUrl()).into(imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (test.getServiceName().equals("停车场")){
                    Intent intent = new Intent(holder.itemView.getContext(), ParkingActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else if (test.getServiceName().equals("城市地铁")){
                    Intent intent = new Intent(holder.itemView.getContext(), CItySubWayActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
