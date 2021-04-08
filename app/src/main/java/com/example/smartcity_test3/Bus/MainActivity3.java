package com.example.smartcity_test3.Bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
private TextView te;
private SharedPreferences.Editor cun;
private int years ;
private  int monthOfYears ;
private int dayOfMonths ;
private DatePicker datePicker;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        te = findViewById(R.id.xianshi);
        Button button = findViewById(R.id.next1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        datePicker = findViewById(R.id.rili);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cun =getSharedPreferences("data",0).edit();
                cun.putString("time",years+"-"+monthOfYears+"-"+dayOfMonths);
                if (years ==0||monthOfYears==0||dayOfMonths==0){
                    Toast.makeText(MainActivity3.this,"please select date",Toast.LENGTH_LONG).show();
                }
                else {
                    cun.apply();
                    Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                    startActivity(intent);
                }


            }
        });
//        datePicker.init();
//        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//               years=year;
//               monthOfYears=monthOfYear;
//               dayOfMonths =dayOfMonth;
//
//                Toast.makeText(MainActivity3.this,""+year+(monthOfYear+1)+dayOfMonth,Toast.LENGTH_SHORT).show();
//
//            te.setText(years+"nian"+(monthOfYears+1)+"yue"+dayOfMonths+"ri");
//            }
//        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                               years=year;
               monthOfYears=month;
               dayOfMonths =dayOfMonth;

                Toast.makeText(MainActivity3.this,""+year+(month+1)+dayOfMonth,Toast.LENGTH_SHORT).show();

            te.setText(years+"nian"+(monthOfYears+1)+"yue"+dayOfMonths+"ri");

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}