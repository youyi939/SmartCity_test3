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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity_test3.R;
import com.example.smartcity_test3.utils.KenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PersonalInfoActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button change_info;
    private TextView idCard_info;
    private RadioButton mail;
    private RadioButton fmail;
    private EditText phone_info;
    private EditText email_info;
    private EditText nikeName_info;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        sharedPreferences = getSharedPreferences("data",0);
        editor = getSharedPreferences("data",0).edit();
        change_info = findViewById(R.id.change_info);
        mail = findViewById(R.id.mail);
        fmail = findViewById(R.id.fmail);
        idCard_info = findViewById(R.id.idCard_info);
        phone_info = findViewById(R.id.phone_info);
        email_info = findViewById(R.id.email_info);
        nikeName_info = findViewById(R.id.nikeName_info);
        imageView = findViewById(R.id.avatar_info);


        String token = sharedPreferences.getString("token","k");
        getUserInfo(token);


        change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(phone_info.getText()) || TextUtils.isEmpty(email_info.getText()) || TextUtils.isEmpty(nikeName_info.getText())){
                    Toast.makeText(PersonalInfoActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    String token = sharedPreferences.getString("token","k");
                    String email = email_info.getText().toString();
                    String phone = phone_info.getText().toString();
                    String nikeName = nikeName_info.getText().toString();
                    int sex = 0;
                    if (fmail.isChecked()){
                        sex = 1;
                    }
                    String url = "http://124.93.196.45:10002/system/user/updata?userId=32213&nickName="+nikeName+"&email="+email+"&phonenumber="+phone+"&sex="+sex;
                    changeUserInfo(url,token);
                }
            }
        });

    }


    public void changeUserInfo(String url,String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtil.Post(url,token,"");
                    JSONObject jsonObject = new JSONObject(json);
                    int code = jsonObject.getInt("code");
                    if (code == 200){
                        handler.sendEmptyMessage(1);
                    }else if (code  == 500){
                        handler.sendEmptyMessage(2);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //获取用户详细信息
    public void getUserInfo(String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://124.93.196.45:10002/getInfo";
                    String json = KenUtil.Get_T(url,token);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject object = jsonObject.getJSONObject("user");
                    String userId = object.getString("userId");
                    String nickName = object.getString("nickName");
                    String email = object.getString("email");
                    String phonenumber = object.getString("phonenumber");
                    int sex = object.getInt("sex");
                    String idCard = object.getString("idCard");
                    String avatar = "http://124.93.196.45:10002"+object.getString("avatar");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nikeName_info.setText(nickName);
                            Glide.with(PersonalInfoActivity.this).load(avatar).into(imageView);
                            email_info.setText(email);
                            phone_info.setText(phonenumber);


                            if (sex==0){
                                mail.setChecked(true);
                                fmail.setChecked(false);
                            }else {
                                mail.setChecked(false);
                                fmail.setChecked(true);
                            }

                            int length = idCard.length();
                            String msg1 = idCard.substring(0,2);
                            String msg2 = idCard.substring(length-4,length);
                            String card  = msg1+"*************"+msg2;
                            idCard_info.setText(card);

                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**\
     *
     * 1:suc
     * 2:def
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(PersonalInfoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(PersonalInfoActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


}