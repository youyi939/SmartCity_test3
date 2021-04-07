package com.example.smartcity_test3.CitySubway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.smartcity_test3.R;

public class CItySubWayActivity extends AppCompatActivity {

    private ListView listView_CitySubway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_ity_sub_way);
        listView_CitySubway = findViewById(R.id.listView_CitySubway);

    }
}