package com.example.smartcity_test3.CitySubway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.CitySubway.adapter.ListViewAdapter_City;
import com.example.smartcity_test3.CitySubway.pojo.Subway;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitySubwayActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter_City adapter_city;
    private List<Subway> subwayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway);
        listView = findViewById(R.id.city_listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CitySubwayActivity.this,CityDetailsActivity.class);
                intent.putExtra("lineId",String.valueOf(subwayList.get(i).getLineId()));
                startActivity(intent);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://124.93.196.45:10002/metro/list?pageNum=1&currentName=建国门";
                    String json = KenUtil.Get(url);
                    JSONObject jsonObject = new JSONObject(json);

                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int lineId = object.getInt("lineId");
                        String lineName = object.getString("lineName");
                        String lastName = object.getString("lastName");
                        int reachTime = object.getInt("reachTime");
                        subwayList.add(new Subway(lineName,lastName,lineId,reachTime));
                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    adapter_city = new ListViewAdapter_City(CitySubwayActivity.this,R.layout.city_item,subwayList);
                    listView.setAdapter(adapter_city);
                    break;
                default:
                    break;
            }
        }
    };


}