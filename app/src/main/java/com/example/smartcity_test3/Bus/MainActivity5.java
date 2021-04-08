package com.example.smartcity_test3.Bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {
private SharedPreferences sharedPreferences;
private TextView name;
private TextView tel;
private TextView shang;
private TextView xia;
private TextView time;
private Button tijiao;
private String a;
  private   String b;
  private String c;
  private String d;
  private String e;
  private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        sharedPreferences = getSharedPreferences("data",0);
         a =sharedPreferences.getString("name","what");
         b = sharedPreferences.getString("tel","how");
         c =sharedPreferences.getString("shangche","which");
         d = sharedPreferences.getString("xiache","todo");
         e =sharedPreferences.getString("time","nono");
         token = sharedPreferences.getString("head","null");
        Log.i("YJH",a+b+c+d+e+token);
        name = findViewById(R.id.name5);
        tel =findViewById(R.id.tel5);
        shang = findViewById(R.id.shang5);
        xia = findViewById(R.id.xia5);
        time=findViewById(R.id.riqi);
        tijiao =findViewById(R.id.tijiao);
        name.setText(a);
        tel.setText(b);
        shang.setText(c);
        xia.setText(d);
        time.setText(e);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity5.this,"sorry```````",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient().newBuilder().build();
                        MediaType mediaType = MediaType.parse("application/json");
                        try {
                            RequestBody body = RequestBody.create(mediaType,httppost());
                            Request request = new Request.Builder()
                                    .url("http://124.93.196.45:10002/userinfo/busOrders")
                                    .method("POST",body)
                                    .addHeader("Authorization",token)
                                    .addHeader("Content-Type","application/json ")
                                    .build();
                            Response response = client.newCall(request).execute();
                            Message message = new Message();
                            message.what=1;
                            handler.sendMessage(message);
                        } catch (JSONException | IOException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private  String httppost() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("start",c);
        obj.put("end",d);
        obj.put("username",a);
        obj.put("userTel",b);
        obj.put("price","10");
        obj.put("path","000");
        obj.put("status","1");
        obj.put("userId","7788");
        Log.i("DSB",c+","+d+","+a+","+b);
        return obj.toString();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                Intent intent = new Intent(MainActivity5.this, MainActivity1.class);
                startActivity(intent);
                break;
                default:
                    break;
            }
        }
    };
}