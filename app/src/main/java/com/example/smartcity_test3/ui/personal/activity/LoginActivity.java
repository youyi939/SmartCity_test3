package com.example.smartcity_test3.ui.personal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity_test3.MainActivity;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText username_login;
    private EditText password_login;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);
        login = findViewById(R.id.login);
        editor = getSharedPreferences("data",0).edit();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username_login.getText())|| TextUtils.isEmpty(password_login.getText())){
                    Toast.makeText(LoginActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String username = username_login.getText().toString();
                                String password = password_login.getText().toString();
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("username",username);
                                jsonObject.put("password",password);
                                String json = KenUtil.Post("http://124.93.196.45:10002/login","",jsonObject.toString());
                                JSONObject object = new JSONObject(json);
                                int code = object.getInt("code");
                                if (code ==200){
                                    String token = object.getString("token");
                                    editor.putString("token",token);
                                    editor.putString("username",username);
                                    editor.putString("password",password);
                                    editor.commit();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("login",true);
                                            startActivity(intent);
                                        }
                                    });

                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
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
        });
    }
}