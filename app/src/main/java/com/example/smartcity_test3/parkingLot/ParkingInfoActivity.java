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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingInfoActivity extends AppCompatActivity {

    private Parking parking;

    @BindView(R.id.name_parkinfo)
    TextView name;

    @BindView(R.id.address_parkinfo)
    TextView address;

    @BindView(R.id.priceCaps_parkinfo)
    TextView priceCaps;

    @BindView(R.id.vacancy_parkinfo)
    TextView vacancy;

    @BindView(R.id.distance_parkinfo)
    TextView distance;

    @BindView(R.id.img_parkInfo)
    ImageView imageView;

    @BindView(R.id.state_parkInfo)
    TextView state_parkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);
        ButterKnife.bind(this);

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