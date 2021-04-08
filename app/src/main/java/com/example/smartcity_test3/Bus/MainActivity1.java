package com.example.smartcity_test3.Bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity1 extends AppCompatActivity {
private List<Big>list= new ArrayList<>();
private ExpandableListView expandableListView;
private SharedPreferences.Editor Token;
//   private String token;
private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        expandableListView =findViewById(R.id.biaoji);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url ="http://124.93.196.45:10002/userinfo/lines/list?pageNum=1&pageSize=10";
                try {
                    String josn = Httpget(url);
                    JSONObject jsonObject = new JSONObject(josn);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String endTime =obj.getString("endTime");
                        String id=obj.getString("id");
                        String name =obj.getString("name");
                        String first =obj.getString("first");
                        String end =obj.getString("end");
                        String startTime =obj.getString("startTime");
                        String price =obj.getString("price");
                        String mileage =obj.getString("mileage");
                        String url1 ="http://124.93.196.45:10002/userinfo/busStop/list?pageNum=1&pageSize=10&linesId="+id;
                        String josn1 =Httpget(url1);
                        JSONObject jsonObject1 = new JSONObject(josn1);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("rows");
                        List<Lit>lits1 = new ArrayList<>();
                        for (int j = 0; j <jsonArray1.length() ; j++) {
                            JSONObject obj2 = jsonArray1.getJSONObject(j);
                            String linesId =obj2.getString("linesId");
                            String stepsId = obj2.getString("stepsId");
                            String names = obj2.getString("name");
                            String sequence = obj2.getString("sequence");
                            lits1.add(new Lit(linesId,stepsId,names,sequence));
                        }
                        list.add(new Big(endTime,id,name,first,end,startTime,price,mileage,lits1));
                    }
                    Message message = new Message();
                    message.what=1;
                    handler.sendMessage(message);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://124.93.196.45:10002/login";
                try {
                    String josn = Httppost(url);
                    JSONObject jsonObject = new JSONObject(josn);
                   String token = jsonObject.getString("token");
                    Log.i("ND", token);
                    Token =getSharedPreferences("data",0).edit();
                    Token.putString("head",token);
                    Token.apply();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


private String Httppost(String url) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\"username\": \"kenchen\",\"password\": \"123456\"}");
    Request request = new Request.Builder()
            .url(url)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
    Response response = client.newCall(request).execute();
    String josn = response.body().string();
    return josn;

}





    private  String Httpget(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String josn = response.body().string();
        return josn;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    adapter = new Adapter(list);
                    expandableListView.setAdapter(adapter);
                    expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                            Intent intent = new Intent(MainActivity1.this,MainActivity2.class);
                            Big big =list.get(groupPosition);
                            intent.putExtra("data",big);
                            startActivity(intent);
                            return true;
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };
}