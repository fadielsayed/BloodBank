package com.example.fadii.bloodbank.viewpagerPK;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.registerPK.LoginActivity;
import com.example.fadii.bloodbank.registerPK.SharedprefManager;
import com.example.fadii.bloodbank.registerPK.SignupActivity;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button toLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);

        viewpagerAdapter viewpagerAdapter = new viewpagerAdapter(this);
        viewPager.setAdapter(viewpagerAdapter);

        toLoginButton = findViewById(R.id.toLoginButton);
        toLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedprefManager.getInstance(this).isloggedIn()){
            Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}
