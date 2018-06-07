package com.example.machine_time.hackaton;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    ServiceFragment serviceFragment;
    WashFragment washFragment;
    ProfileFragment profileFragment;
    FragmentTransaction fTrans;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fTrans = getSupportFragmentManager().beginTransaction();
            boolean result = false;

            switch (item.getItemId()) {
                case R.id.navService:
                    fTrans.replace(R.id.mainLinear, serviceFragment);
                    result = true;
                    break;
                case R.id.navProfile:
                    fTrans.replace(R.id.mainLinear, profileFragment);
                    result = true;
                    break;
                case R.id.navWash:
                    fTrans.replace(R.id.mainLinear, washFragment);
                    result = true;
                    break;
            }
            fTrans.commit();
            return result;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        serviceFragment = new ServiceFragment();
        washFragment = new WashFragment();
        profileFragment = new ProfileFragment();

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.mainLinear, serviceFragment);
        fTrans.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
