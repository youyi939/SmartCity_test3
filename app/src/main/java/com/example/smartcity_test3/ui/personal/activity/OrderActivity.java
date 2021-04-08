package com.example.smartcity_test3.ui.personal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.ui.personal.adapter.OrderAdapter;
import com.example.smartcity_test3.ui.personal.pojo.Order;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private ListView listView_order;
    private List<Order> orderList = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView_order = findViewById(R.id.listView_order);
        sharedPreferences  =getSharedPreferences("data",0);

        getOrderList();
    }



    public void getOrderList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token = sharedPreferences.getString("token","k");
                    String url = "http://124.93.196.45:10002/userinfo/orders/list?&userId=2";
                    String json = KenUtil.Get_T(url,token);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String createTime = object.getString("createTime");
                        String orderNum = object.getString("orderNum");
                        orderList.add(new Order(orderNum,createTime));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("Ken", "run: "+orderList.size());
                            OrderAdapter adapter = new OrderAdapter(OrderActivity.this,R.layout.item_order,orderList);
                            listView_order.setAdapter(adapter);

                        }
                    });

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


}