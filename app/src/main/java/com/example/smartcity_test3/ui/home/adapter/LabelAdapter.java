package com.example.smartcity_test3.ui.home.adapter;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.home.pojo.Item;

import java.util.List;


public class LabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> list;
    private RecyclerView.ViewHolder holder;
    private Handler handler;


    public LabelAdapter(List<Item> itemList, Handler handler) {
        this.list = itemList;
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label,parent,false);
        holder = new RecyclerView.ViewHolder(view) {};

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item test = list.get(position);
        TextView name = holder.itemView.findViewById(R.id.name_label);
        name.setText(test.getDictLabel());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = 5;
                message.obj = position;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
