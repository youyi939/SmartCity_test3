package com.example.smartcity_test3.ui.personal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText old_change;
    private EditText new_change;
    private Button changePassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        old_change  =findViewById(R.id.old_change);
        new_change = findViewById(R.id.new_change);
        changePassword = findViewById(R.id.changePassword);
        sharedPreferences = getSharedPreferences("data",0);
        editor = getSharedPreferences("data",0).edit();

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(old_change.getText()) || TextUtils.isEmpty(new_change.getText())){
                    Toast.makeText(ChangePasswordActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    String old = sharedPreferences.getString("password","1");
                    if (!old.equals(old_change.getText().toString())){
                        Toast.makeText(ChangePasswordActivity.this,"原密码输入错误",Toast.LENGTH_SHORT).show();
                    }else {
                        String url = "http://124.93.196.45:10002/system/user/resetPwd";
                        String token = sharedPreferences.getString("token","k");
                        String password1  = old_change.getText().toString();
                        String password2  = new_change.getText().toString();
                        String userId = sharedPreferences.getString("userId","32213");
                        change(url,token,password1,password2,userId);
                    }
                }
            }
        });
    }

    public void change(String url,String token,String password1,String password2,String userId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userId",userId);
                    jsonObject.put("oldPwd",password1);
                    jsonObject.put("password",password2);
                    String json = KenUtil.Put_T(url,token,jsonObject.toString());
                    JSONObject object = new JSONObject(json);
                    int code  = object.getInt("code");
                    if (code ==  200){
                        editor.putString("password",password2);
                        editor.commit();
                        handler.sendEmptyMessage(1);
                    }else  if (code == 500){
                        handler.sendEmptyMessage(2);
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(ChangePasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(ChangePasswordActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };











}