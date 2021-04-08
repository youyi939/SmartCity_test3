package com.example.smartcity_test3.Bus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.smartcity_test3.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseExpandableListAdapter {
    private List<Big>list = new ArrayList<>();

    public Adapter(List<Big> list) {
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getLits().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getLits().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Big big = list.get(groupPosition);
        if (convertView == null){
convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fu,parent,false);
        }
        TextView qid = convertView.findViewById(R.id.qid);
        TextView fu = convertView.findViewById(R.id.fu);
        TextView yunxingshijian = convertView.findViewById(R.id.yunxingshijian);
        TextView zhongd = convertView.findViewById(R.id.zhongd);
        TextView piaojia = convertView.findViewById(R.id.piaojia);
        TextView lic = convertView.findViewById(R.id.lic);
        fu.setText(big.getName());
        qid.setText(big.getFirst());
        zhongd.setText(big.getEnd());
        yunxingshijian.setText(big.getStartTime()+"------->"+big.getEndTime());
        piaojia.setText(big.getPrice());
        lic.setText(big.getMileage());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Lit lit = list.get(groupPosition).getLits().get(childPosition);
        if ( convertView == null){
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zi,parent,false);
        }
        TextView zi = convertView.findViewById(R.id.zi);

        zi.setText(lit.getNames());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
}
