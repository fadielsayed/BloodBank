package com.example.fadii.bloodbank.navigationScreens.favoritePk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fadii.bloodbank.MAIN.ArticlesPK.ArticleData;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.NotifyPK.NotifyActivity;
import com.example.fadii.bloodbank.navigationScreens.favoritePk.NotificationCount.NotificationData;
import com.example.fadii.bloodbank.navigationScreens.favoritePk.NotificationCount.NotificationExample;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.Rertrofitinstance;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritActivity extends AppCompatActivity {

    private RecyclerView myrecyclerView3;
    private List<FavouriteData> lstfavourite;
    TextView smsCountTxt;
//    int pendingSMSCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.favtoolbar_ID);
        setSupportActionBar(toolbar);
        myrecyclerView3 = findViewById(R.id.myRecycler3ID);
        myrecyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final Api dataservices = Rertrofitinstance.getservice();
        Call<FavouriteExample> call = dataservices.getFavoutite("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<FavouriteExample>() {
            @Override
            public void onResponse(Call<FavouriteExample> call, Response<FavouriteExample> response) {

                ArrayList<FavouriteDatum> datumList = new ArrayList<>();
                datumList = response.body().getData().getData();

                Log.i("datumList", datumList.size() + "  ----" + response.body().getStatus());
                favouriteAdapter recyclerviewAdapter1 = new favouriteAdapter(getApplicationContext(), datumList);
                myrecyclerView3.setAdapter(recyclerviewAdapter1);

            }

            @Override
            public void onFailure(Call<FavouriteExample> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        return super.onPrepareOptionsMenu(menu);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        final MenuItem menuItem = menu.findItem(R.id.notify_main_menuID);
//
//        View actionView = MenuItemCompat.getActionView(menuItem);
//        smsCountTxt = actionView.findViewById(R.id.notification_badge);
//
//       // setupBadge();
//
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(menuItem);
//            }
//        });
//
//        getCount();
//
////        setupBadge();
//
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(FavouritActivity.this,NotifyActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_menuID:
                return true;
            case R.id.notify_menuID:
                Intent intent =new Intent(FavouritActivity.this,NotifyActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getCount(){

        Call<NotificationExample> call= RetrofitClient.getInstance()
                .getApi().getCount("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<NotificationExample>() {
            @Override
            public void onResponse(Call<NotificationExample> call, Response<NotificationExample> response) {
                NotificationExample notificationExample = response.body();
                smsCountTxt.setText(notificationExample.getData().getNotificationsCount());
            }

            @Override
            public void onFailure(Call<NotificationExample> call, Throwable t) {

            }
        });


    }




//    private void setupBadge() {
//
//        if (smsCountTxt != null) {
//            if (pendingSMSCount == 0) {
//                if (smsCountTxt.getVisibility() != View.GONE) {
//                    smsCountTxt.setVisibility(View.GONE);
//                }
//            } else {
//                smsCountTxt.setText(String.valueOf(Math.min(pendingSMSCount, 99)));
//                if (smsCountTxt.getVisibility() != View.VISIBLE) {
//                    smsCountTxt.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//    }

}
