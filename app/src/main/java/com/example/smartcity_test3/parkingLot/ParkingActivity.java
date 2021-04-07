package com.example.smartcity_test3.parkingLot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.parkingLot.adapter.ParkingLotAdapter;
import com.example.smartcity_test3.parkingLot.pojo.Parking;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingActivity extends AppCompatActivity {

    private List<Parking> parkingList = new ArrayList<>();
    private List<Parking> parkingList2 = new ArrayList<>();

    @BindView(R.id.linnerlayout_parking)
    LinearLayout linnerlayout_parking;

    @BindView(R.id.listView_parking)
    ListView listView_parking;

    @BindView(R.id.more_parking)
    Button more_parking;
    private ParkingLotAdapter adapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.parking_menu:
                Intent intent = new Intent(ParkingActivity.this,ParkJiLuActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        ButterKnife.bind(this);

        more_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(2);
                linnerlayout_parking.removeView(more_parking);
            }
        });

        listView_parking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ParkingActivity.this,ParkingInfoActivity.class);
                intent.putExtra("id",parkingList.get(i).getId());
                startActivity(intent);
            }
        });

        getParkingLot();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    parkingList.sort(new Comparator<Parking>() {
                        @Override
                        public int compare(Parking parking, Parking t1) {
                            int a = Integer.parseInt(parking.getDistance());
                            int b = Integer.parseInt(t1.getDistance());
                            return a - b;
                        }
                    });
                    if (parkingList.size() > 5) {
                        parkingList2 = parkingList.subList(0, 5);
                        adapter = new ParkingLotAdapter(ParkingActivity.this, R.layout.item_parking, parkingList2);
                        listView_parking.setAdapter(adapter);
                    } else {
                        adapter = new ParkingLotAdapter(ParkingActivity.this, R.layout.item_parking, parkingList);
                        listView_parking.setAdapter(adapter);
                    }
                    break;
                case 2:
                    adapter = new ParkingLotAdapter(ParkingActivity.this, R.layout.item_parking, parkingList);
                    listView_parking.setAdapter(adapter);
                    break;
            }
        }
    };

    public void getParkingLot() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Get("http://124.93.196.45:10002/userinfo/parklot/list?pageNum=1&pageSize=20");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String parkName = object.getString("parkName");
                        String vacancy = object.getString("vacancy");
                        String priceCaps = object.getString("priceCaps");
                        String imgUrl = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        String rates = object.getString("rates");
                        String address = object.getString("address");
                        String distance = object.getString("distance");
                        String allPark = object.getString("allPark");
                        int id = object.getInt("id");
                        parkingList.add(new Parking(id,parkName, vacancy, priceCaps, imgUrl, rates, address, distance, allPark));
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}