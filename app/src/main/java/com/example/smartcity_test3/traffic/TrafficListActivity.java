package com.example.smartcity_test3.traffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.traffic.adapter.TrafficAdapter;
import com.example.smartcity_test3.traffic.pojo.Traffic;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrafficListActivity extends AppCompatActivity {


    private List<Traffic> trafficList = new ArrayList<>();
    private List<Traffic> trafficList2 = new ArrayList<>();
    private ListView listView_traffic;
    private TrafficAdapter adapter;
    private Button findAll_traffic;
    private LinearLayout linearLayout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_list);
        listView_traffic = findViewById(R.id.listView_traffic);
        findAll_traffic = findViewById(R.id.findAll_traffic);
        linearLayout = findViewById(R.id.linnerlayout_traffic);
        sharedPreferences = getSharedPreferences("data",0);

        findAll_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new TrafficAdapter(TrafficListActivity.this,R.layout.traffic_item,trafficList);
                listView_traffic.setAdapter(adapter);
                linearLayout.removeView(findAll_traffic);
                Log.i("Ken", "onClick: "+trafficList.size());
            }
        });

        listView_traffic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 =new Intent(TrafficListActivity.this,TrafficInfoActivity.class);
                intent1.putExtra("traffic",trafficList.get(i));
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

            String engine = sharedPreferences.getString("engine","engine");
            String number = sharedPreferences.getString("number","number");
            getTrafficList(engine,number);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ken", "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ken", "onResume: ");
    }

    public void getTrafficList(String engine, String number){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://124.93.196.45:10002/userinfo/illegal/list?pageNum=1&pageSize=10&catType=小型汽车 &engineNumber="+engine+"&licencePlate="+number;
                    String json = KenUtil.Get(url);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int id = object.getInt("id");
                        String licencePlate = object.getString("licencePlate");
                        String disposeState = object.getString("disposeState");
                        String badTime = object.getString("badTime");
                        String money = object.getString("money");
                        String deductMarks = object.getString("deductMarks");
                        String illegalSites = object.getString("illegalSites");
                        String noticeNo = object.getString("noticeNo");
                        String engineNumber = object.getString("engineNumber");
                        String catType = object.getString("catType");
                        String trafficOffence = object.getString("trafficOffence");
                        trafficList.add(new Traffic(id,licencePlate,disposeState,badTime,money,deductMarks,illegalSites,noticeNo,engineNumber,catType,trafficOffence));
                    }

                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 点击查询
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    if (trafficList.size() > 5){
                        trafficList2 = trafficList.subList(0,5);
                        adapter = new TrafficAdapter(TrafficListActivity.this,R.layout.traffic_item,trafficList2);
                        listView_traffic.setAdapter(adapter);
                    }else {
                        adapter = new TrafficAdapter(TrafficListActivity.this,R.layout.traffic_item,trafficList);
                        listView_traffic.setAdapter(adapter);
                    }
                    break;
                default:
                    break;
            }
        }
    };

}