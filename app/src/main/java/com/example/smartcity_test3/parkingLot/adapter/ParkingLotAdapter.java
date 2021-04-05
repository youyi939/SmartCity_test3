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
import com.example.smartcity_test3.parkingLot.pojo.Parking;

import java.util.List;

public class ParkingLotAdapter extends ArrayAdapter<Parking> {

    private int resourceId;

    public ParkingLotAdapter(@NonNull Context context, int resource, @NonNull List<Parking> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        Parking parking = getItem(position);
        TextView name = convertView.findViewById(R.id.name_parking);
        TextView priceCaps = convertView.findViewById(R.id.priceCaps_parking);
        TextView address = convertView.findViewById(R.id.address_parking);
        TextView vacancy = convertView.findViewById(R.id.vacancy_parking);
        TextView distance = convertView.findViewById(R.id.distance_parking);

        name.setText("停车场名称："+parking.getParkName());
        priceCaps.setText("费率："+parking.getRates());
        address.setText("地址："+parking.getAddress());
        vacancy.setText("空位："+parking.getVacancy());
        distance.setText("距离："+parking.getDistance());

        return convertView;
    }
}
