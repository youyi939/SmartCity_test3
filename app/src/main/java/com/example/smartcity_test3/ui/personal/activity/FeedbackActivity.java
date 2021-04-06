package com.example.smartcity_test3.ui.personal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FeedbackActivity extends AppCompatActivity {

    private Button submit;
    private SharedPreferences sharedPreferences;
    private EditText feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feed = findViewById(R.id.feed);
        submit = findViewById(R.id.submit);
        sharedPreferences = getSharedPreferences("data",0);
        String token  = sharedPreferences.getString("token","k");
        String userId  = sharedPreferences.getString("userId","k");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(feed.getText())){
                    Toast.makeText(FeedbackActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    String url = "http://124.93.196.45:10002/userinfo/feedback";
                    feedback(url,token,userId,feed.getText().toString());

                }
            }
        });
    }


    public void feedback(String url,String token,String userId,String content){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("content",content);
                    jsonObject.put("userId",userId);
                    String json = KenUtil.Post(url,token,jsonObject.toString());
                    JSONObject object = new JSONObject(json);
                    int code = object.getInt("code");
                    if (code == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FeedbackActivity.this,"新增意见反馈成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FeedbackActivity.this,"新增意见反馈失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}