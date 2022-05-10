package com.example.fadii.bloodbank.navigationScreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fadii.bloodbank.MAIN.DonatesPK.ReqdonationFragment;
import com.example.fadii.bloodbank.MAIN.ViewPagerAdapter;
import com.example.fadii.bloodbank.MAIN.ArticlesPK.articalsFragment;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.DonateRequestDetail.DonationrequestActivity;
import com.example.fadii.bloodbank.navigationScreens.CallUsPK.CallusActivity;
import com.example.fadii.bloodbank.navigationScreens.ModifyUserDataPK.ModifyDataActivity;
import com.example.fadii.bloodbank.navigationScreens.favoritePk.FavouritActivity;
import com.example.fadii.bloodbank.registerPK.LoginActivity;
import com.example.fadii.bloodbank.registerPK.SharedprefManager;

public class DashboardActivity extends AppCompatActivity {


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.info_menu_ID:
                        Intent intent = new Intent(DashboardActivity.this,ModifyDataActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.notify_settingmenu_ID:
                        Intent I = new Intent(DashboardActivity.this,NotifSittingActivity.class);
                        startActivity(I);
                        break;

                    case R.id.report_menuID:
                        Intent P = new Intent(DashboardActivity.this,ReportActivity.class);
                        startActivity(P);
                        break;

                    case R.id.home_menuID:
                        Intent G = new Intent(DashboardActivity.this,DashboardActivity.class);
                        startActivity(G);
                        break;

                    case R.id.contactus_menuID:
                        Intent H = new Intent(DashboardActivity.this,CallusActivity.class);
                        startActivity(H);
                        break;

                    case R.id.fav_menuID:
                        Intent K= new Intent(DashboardActivity.this,FavouritActivity.class);
                        startActivity(K);
                        break;

                    case R.id.aboutapp_menuID:
                        Intent A = new Intent(DashboardActivity.this,AboutAppActivity.class);
                        startActivity(A);
                        break;

                    case R.id.signout_menu_ID:
                        logout();
                        break;


                }

                return true;
            }
        });


        tabLayout =  findViewById(R.id.tablelayout_id);
        viewPager = findViewById(R.id.viewpager_id);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new articalsFragment(),"المقالات");
        adapter.AddFragment(new ReqdonationFragment(),"طلبات التبرع");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,DonationrequestActivity.class);
                startActivity(intent);
            }
        });


    }

    private void logout() {

        SharedprefManager.getInstance(DashboardActivity.this).clear();
        Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}
