package com.example.smartcity_test3.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;
import com.example.smartcity_test3.traffic.pojo.Traffic;


public class TrafficInfoActivity extends AppCompatActivity {

    private Traffic traffic;
    private TextView trafficOffence_info;
    private TextView illegalSites_info;
    private TextView badTime_info;
    private TextView deductMarks_info;
    private TextView money_info;
    private TextView noticeNo_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_info);
        noticeNo_info = findViewById(R.id.noticeNo_info);
        money_info = findViewById(R.id.money_info);
        deductMarks_info = findViewById(R.id.deductMarks_info);
        badTime_info = findViewById(R.id.badTime_info);
        illegalSites_info = findViewById(R.id.illegalSites_info);
        trafficOffence_info = findViewById(R.id.trafficOffence_info);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("traffic") != null){
            traffic = (Traffic) intent.getSerializableExtra("traffic");
            trafficOffence_info.setText("违法信息："+traffic.getTrafficOffence());
            illegalSites_info.setText("违法地址："+traffic.getIllegalSites());
            badTime_info.setText("违法时间："+traffic.getBadTime());
            money_info.setText("罚款金额："+traffic.getMoney());
            deductMarks_info.setText("违法扣分："+traffic.getDeductMarks());
            noticeNo_info.setText("通知单号："+traffic.getNoticeNo());
        }

    }
}