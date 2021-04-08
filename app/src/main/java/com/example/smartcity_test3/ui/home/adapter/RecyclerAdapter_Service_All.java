package com.example.smartcity_test3.ui.home.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.Bus.MainActivity1;
import com.example.smartcity_test3.CitySubway.CitySubwayActivity;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.ParkingActivity;
import com.example.smartcity_test3.traffic.TrafficActivity;
import com.example.smartcity_test3.ui.home.pojo.Item_Service;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class RecyclerAdapter_Service_All extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.ViewHolder holder;
    private List<Item_Service> item_serviceList;

    public RecyclerAdapter_Service_All(List<Item_Service> itemList) {
        this.item_serviceList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_layout,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item_Service item_service = item_serviceList.get(position);
        TextView title = holder.itemView.findViewById(R.id.item_title_all);
        ShapeableImageView imageView = holder.itemView.findViewById(R.id.item_img_all);
        title.setText(item_service.getServiceName());
        Glide.with(holder.itemView).load(item_service.getImgUrl()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(holder.itemView.getContext(),item_service.getServiceName(),Toast.LENGTH_SHORT).show();
                if (item_service.getServiceName().equals("停车场")){
                    Intent intent = new Intent(holder.itemView.getContext(), ParkingActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else if (item_service.getServiceName().equals("违章查询")){
                    Intent intent = new Intent(holder.itemView.getContext(), TrafficActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else if (item_service.getServiceName().equals("城市地铁")){
                    Intent intent = new Intent(holder.itemView.getContext(), CitySubwayActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else if (item_service.getServiceName().equals("智慧巴士")){
                    Intent intent = new Intent(holder.itemView.getContext(), MainActivity1.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_serviceList.size();
    }
}
