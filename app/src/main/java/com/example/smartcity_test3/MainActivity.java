package com.example.smartcity_test3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartcity_test3.ui.Xinwen.XinwenFragment;
import com.example.smartcity_test3.ui.dashboard.DashboardFragment;
import com.example.smartcity_test3.ui.home.HomeFragment;
import com.example.smartcity_test3.ui.notifications.NotificationsFragment;
import com.example.smartcity_test3.ui.personal.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private NotificationsFragment notificationsFragment = new NotificationsFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();
    private PersonalFragment personalFragment = new PersonalFragment();
    private XinwenFragment xinwenFragment = new XinwenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_home);
        replace(homeFragment);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        replace(homeFragment);
                        break;
                    case R.id.navigation_notifications:
                        replace(notificationsFragment);
                        break;
                    case R.id.navigation_dashboard:
                        replace(dashboardFragment);
                        break;
                    case R.id.navigation_personal:
                        replace(personalFragment);
                        break;
                    case R.id.navigation_xinwen:
                        replace(xinwenFragment);
                        break;
                }
                return true;
            }
        });

        Intent intent = getIntent();
        if (intent.getBooleanExtra("login",false)){
            replace(personalFragment);
        }

    }

    public void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}