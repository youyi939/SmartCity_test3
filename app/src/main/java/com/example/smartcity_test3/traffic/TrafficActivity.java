package com.example.smartcity_test3.traffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

import java.util.ArrayList;
import java.util.List;

public class TrafficActivity extends AppCompatActivity {

    private Button find_traffic;
    private EditText number_traffic;
    private EditText engineNumber_traffic;
    private Spinner spinner_traffic;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter_spinner;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        find_traffic = findViewById(R.id.find_traffic);
        engineNumber_traffic = findViewById(R.id.engineNumber_traffic);
        number_traffic = findViewById(R.id.number_traffic);
        spinner_traffic = findViewById(R.id.spinner_traffic);
        adapter_spinner = new ArrayAdapter<String>(TrafficActivity.this,R.layout.support_simple_spinner_dropdown_item,list);
        adapter_spinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        list.add("京");
        list.add("辽");
        spinner_traffic.setAdapter(adapter_spinner);
        editor = getSharedPreferences("data",0).edit();

        find_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(number_traffic.getText()) || TextUtils.isEmpty(engineNumber_traffic.getText())){
                    Toast.makeText(TrafficActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                }else {
                    String engine = engineNumber_traffic.getText().toString();
                    String number = list.get(spinner_traffic.getSelectedItemPosition())+number_traffic.getText().toString();
                    editor.putString("engine",engine);
                    editor.putString("number",number);
                    editor.commit();
                    Intent intent = new Intent(TrafficActivity.this,TrafficListActivity.class);
                    startActivity(intent);
                }
            }
        });

    }





}