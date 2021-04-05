package com.example.smartcity_test3.parkingLot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.pojo.Parking;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ParkingInfoActivity extends AppCompatActivity {

    private Parking parking;
    private TextView name;
    private TextView address;
    private TextView priceCaps;
    private TextView vacancy;
    private TextView distance;
    private ImageView imageView;
    private TextView state_parkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);
        name = findViewById(R.id.name_parkinfo);
        address = findViewById(R.id.address_parkinfo);
        priceCaps = findViewById(R.id.priceCaps_parkinfo);
        vacancy = findViewById(R.id.vacancy_parkinfo);
        distance = findViewById(R.id.distance_parkinfo);
        imageView = findViewById(R.id.img_parkInfo);
        state_parkInfo = findViewById(R.id.state_parkInfo);
        Intent intent = getIntent();
        if (intent.getIntExtra("id",0)!=0){
            getParkingLot(intent.getIntExtra("id",0));
        }


    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Log.i("parkinfo", "handleMessage: "+parking.toString());
                    Glide.with(ParkingInfoActivity.this).load(parking.getImgUrl()).into(imageView);
                    name.setText("名称："+parking.getParkName());
                    address.setText("地址："+parking.getAddress());
                    priceCaps.setText("最高收费："+parking.getPriceCaps());
                    vacancy.setText("空位："+parking.getVacancy());
                    distance.setText("距离："+parking.getDistance());
                    state_parkInfo.setText("开放。");
                    break;
            }
        }
    };



    public void getParkingLot(int id) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/userinfo/parklot/"+id);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject object = jsonObject.getJSONObject("data");
                        String parkName = object.getString("parkName");
                        String vacancy = object.getString("vacancy");
                        String priceCaps = object.getString("priceCaps");
                        String imgUrl = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        String rates = object.getString("rates");
                        String address = object.getString("address");
                        String distance = object.getString("distance");
                        String allPark = object.getString("allPark");
                        int id = object.getInt("id");
                        parking = new Parking(id,parkName, vacancy, priceCaps, imgUrl, rates, address, distance, allPark);
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}