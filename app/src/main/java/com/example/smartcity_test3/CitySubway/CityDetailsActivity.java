package com.example.smartcity_test3.CitySubway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_test3.CitySubway.adapter.TimeRecyclerAdapter;
import com.example.smartcity_test3.CitySubway.pojo.Info;
import com.example.smartcity_test3.CitySubway.pojo.SubData;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityDetailsActivity extends AppCompatActivity {

    private List<SubData> subDataList = new ArrayList<>();
    private Info info;
    private TextView startStation;
    private TextView endStation;
    private TextView reachTime;
    private TextView jiange;
    private TextView reachKm;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TimeRecyclerAdapter recyclerAdapter;


    /**
     * 创建ActionBar菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu2,menu);
        return true;
    }

    /**
     * actionBar按钮点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.my_btn:
                Toast.makeText(this, "124", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);
        startStation  = findViewById(R.id.city_details_start);
        endStation = findViewById(R.id.city_details_end);
        reachKm = findViewById(R.id.city_details_reachKM);
        reachTime = findViewById(R.id.city_details_reachTime);
        jiange = findViewById(R.id.city_details_jiange);
        recyclerView = findViewById(R.id.subway_recycler);
        linearLayoutManager = new LinearLayoutManager(CityDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        actionBar = getSupportActionBar();

        /**
         * 设置ActionBar返回按钮
         */
        actionBar.setDisplayShowTitleEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
        actionBar.setDisplayHomeAsUpEnabled(true); // 有小箭头，并且图标可以点击
        actionBar.setDisplayShowHomeEnabled(false);// 使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = getIntent();
                    String lineId = intent.getStringExtra("lineId");
                    String url = "http://124.93.196.45:10002/metro/"+lineId;
                    String json = KenUtil.Get(url);

                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject object = jsonObject.getJSONObject("data");

                    String lineName = object.getString("name");
                    String remainingTime  = object.getString("remainingTime");              //还剩多长时间到达
                    String stationsNumber = object.getString("stationsNumber");             //线路总共的站数
                    int km = object.getInt("km");                                           //还剩多少km到达
                    String runStationsName = object.getString("runStationsName");           //当前在哪一站

                    info = new Info(lineName,remainingTime,stationsNumber,km,runStationsName);
                    Log.i("Ken", "run: "+info.toString());

                    JSONArray jsonArray = object.getJSONArray("metroStepsList");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        int id = object1.getInt("id");
                        String name = object1.getString("name");
                        int seq = object1.getInt("seq");
                        subDataList.add(new SubData(id,name,seq));
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
                    startStation.setText(subDataList.get(0).getName());
                    endStation.setText(subDataList.get(subDataList.size()-1).getName());
                    actionBar.setTitle(info.getLineName());
                    reachTime.setText("剩余到站时间："+info.getRemainingTime()+" min");
                    reachKm.setText("剩余到站距离："+info.getKm()+" km");

                    recyclerAdapter = new TimeRecyclerAdapter(subDataList,info.getRunStationsName());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(recyclerAdapter);

                    break;
                default:
                    break;
            }
        }
    };


}

