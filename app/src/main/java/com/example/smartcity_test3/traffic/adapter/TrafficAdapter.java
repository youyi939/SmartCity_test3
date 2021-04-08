package com.example.smartcity_test3.traffic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.traffic.pojo.Traffic;

import java.util.List;

public class TrafficAdapter extends ArrayAdapter<Traffic> {

    private int resourceId;
    public TrafficAdapter(@NonNull Context context, int resource, @NonNull List<Traffic> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }
        Traffic traffic = getItem(position);
        TextView disposeState = convertView.findViewById(R.id.disposeState_traffic);
        TextView badTime_traffic = convertView.findViewById(R.id.badTime_traffic);
        TextView illegalSites_traffic = convertView.findViewById(R.id.illegalSites_traffic);
        TextView deductMarks_traffic = convertView.findViewById(R.id.deductMarks_traffic);
        TextView money_traffic = convertView.findViewById(R.id.money_traffic);

        if (traffic.getDisposeState().equals(1)){
            disposeState.setText("处理状态:"+"已处理");
        }else {
            disposeState.setText("处理状态:"+"未处理");
        }

        badTime_traffic.setText("违章时间"+traffic.getBadTime());
        illegalSites_traffic.setText("违章地点"+traffic.getIllegalSites());

        deductMarks_traffic.setText("违章记分"+traffic.getDeductMarks());
        money_traffic.setText("罚款金额"+traffic.getMoney());

        return convertView;
    }
}
