package com.example.smartcity_test3.Bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test3.R;

public class MainActivity2 extends AppCompatActivity {
private TextView ac1;
    private TextView ac2;
    private TextView ac3;
    private TextView ac4;
    private TextView ac5;
    private Button btn;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn =findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent1);

            }
        });
        Intent intent = getIntent();
        Big big = (Big) intent.getSerializableExtra("data");
        ac1 =findViewById(R.id.ac1);
        ac2 =findViewById(R.id.ac2);
        ac3 =findViewById(R.id.ac3);
        ac4 =findViewById(R.id.ac4);
        ac5 =findViewById(R.id.ac5);
        ac1.setText(big.getName());
        ac2.setText(big.getId());
        ac3.setText(big.getFirst());
        ac4.setText(big.getEnd());
        ac5.setText(big.getPrice());
        edit =getSharedPreferences("Key",0).edit();
        edit.putString("first",big.getFirst());
        edit.putString("end" ,big.getEnd());
        edit.apply();

    }

}