package com.example.smartcity_test3.Bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

public class MainActivity4 extends AppCompatActivity {
private SharedPreferences sharedPreferences;
private TextView qidian;
private TextView zhongdian;
private EditText name;
private EditText phone;
private EditText shang;
private EditText xia;
private Button deng;
private SharedPreferences.Editor cun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        sharedPreferences =getSharedPreferences("Key",0);
       String a = sharedPreferences.getString("first","111");
        String b =sharedPreferences.getString("end","222");
        Log.i("SSA", a+b);
        qidian=findViewById(R.id.qidian);
        zhongdian=findViewById(R.id.zhongdian);
        name =findViewById(R.id.chengkename);
        phone=findViewById(R.id.chengkepthone);
        shang =findViewById(R.id.chengkeshang);
        xia =findViewById(R.id.chengkexia);
        deng =findViewById(R.id.denglu);
        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(phone.getText())||TextUtils.isEmpty(shang.getText())||TextUtils.isEmpty(xia.getText())){
                    Toast.makeText(MainActivity4.this,"111",Toast.LENGTH_SHORT).show();
                }else {
                    String names = name.getText().toString();
                    String tel =phone.getText().toString();
                    String shangche =shang.getText().toString();
                    String xiache = xia.getText().toString();
                    cun = getSharedPreferences("data",0).edit();
                    cun.putString("name",names);
                    cun.putString("tel",tel);
                    cun.putString("shangche",shangche);
                    cun.putString("xiache",xiache);
                    cun.apply();
                    Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                    startActivity(intent);
                }
            }
        });









        qidian.setText(a);
        zhongdian.setText(b);

    }
}