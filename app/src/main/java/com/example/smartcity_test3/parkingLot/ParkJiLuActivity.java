package com.example.smartcity_test3.parkingLot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.adapter.ParkJiLuAdapter;
import com.example.smartcity_test3.parkingLot.pojo.Jilu;
import com.example.smartcity_test3.parkingLot.pojo.Parking;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkJiLuActivity extends AppCompatActivity {
    private ListView listView_jilu;
    private Button more;
    private Button chaxun;
    private EditText in_jilu;
    private EditText out_jilu;
    private List<Jilu> jiluList = new ArrayList<>();
    private List<Jilu> jiluList2 = new ArrayList<>();
    private ParkJiLuAdapter adapter;
    private LinearLayout linnerlayout_jilu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_ji_lu);
        listView_jilu = findViewById(R.id.listView_jilu);
        more = findViewById(R.id.more_jilu);
        in_jilu = findViewById(R.id.in_jilu);
        out_jilu = findViewById(R.id.out_jilu);
        chaxun = findViewById(R.id.chaxun_jilu);
        linnerlayout_jilu = findViewById(R.id.linnerlayout_jilu);

        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(in_jilu.getText()) || TextUtils.isEmpty(out_jilu.getText())){
                    Toast.makeText(ParkJiLuActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    jiluList.clear();
                    String in = in_jilu.getText().toString();
                    String out = out_jilu.getText().toString();
                    getTime(in,out);
                }
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(2);
                linnerlayout_jilu.removeView(more);
            }
        });

        getParkingLot();

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.i("jilu", "handleMessage: "+jiluList.size());
                    if (jiluList.size()>5){
                        jiluList2 = jiluList.subList(0,5);
                        adapter = new ParkJiLuAdapter(ParkJiLuActivity.this,R.layout.item_jilu,jiluList2);
                        listView_jilu.setAdapter(adapter);
                    }else {
                        adapter = new ParkJiLuAdapter(ParkJiLuActivity.this,R.layout.item_jilu,jiluList);
                        listView_jilu.setAdapter(adapter);
                    }
                    break;
                case 2:
                    adapter = new ParkJiLuAdapter(ParkJiLuActivity.this,R.layout.item_jilu,jiluList);
                    listView_jilu.setAdapter(adapter);
                    break;
            }
        }
    };



    public void getTime(String out,String in) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String url = "http://124.93.196.45:10002/userinfo/parkrecord/list?pageNum=1&outTime="+out+"&entryTime="+in;
                    String json = KenUtil.Get(url);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String entryTime = object.getString("entryTime");
                        String outTime = object.getString("outTime");
                        String plateNumber = object.getString("plateNumber");
                        String monetary = object.getString("monetary");
                        String parkName = object.getString("parkName");
                        jiluList.add(new Jilu(entryTime,outTime,plateNumber,monetary,parkName));
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }



    public void getParkingLot() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://124.93.196.45:10002/userinfo/parkrecord/list?pageNum=1";
                    String json = KenUtil.Get(url);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String entryTime = object.getString("entryTime");
                        String outTime = object.getString("outTime");
                        String plateNumber = object.getString("plateNumber");
                        String monetary = object.getString("monetary");
                        String parkName = object.getString("parkName");
                        jiluList.add(new Jilu(entryTime,outTime,plateNumber,monetary,parkName));
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}