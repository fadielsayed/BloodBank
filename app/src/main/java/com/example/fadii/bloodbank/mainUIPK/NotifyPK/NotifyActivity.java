package com.example.fadii.bloodbank.mainUIPK.NotifyPK;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.DonateRequestDetail.DonationrequestActivity;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifyActivity extends AppCompatActivity {

    private RecyclerView myrecyclerView4;
    private List<Notify_Data> lstNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.notiftoolbar_IDD);
        setSupportActionBar(toolbar);

        myrecyclerView4 = findViewById(R.id.notify_recycler);
        myrecyclerView4.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getNotify();

    }


    public void getNotify(){

        Call<Notify_Example> call = RetrofitClient.getInstance()
                .getApi().
                getNotify("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<Notify_Example>() {
            @Override
            public void onResponse(Call<Notify_Example> call, Response<Notify_Example> response) {

                Notify_Example  example = response.body();
                List<Notify_Datum> datumList = example.getData().getData();
                Log.i("datumList", datumList.size() + "  ----" + response.body().getStatus());
                NotifyAdapter recyclerviewAdapter1 = new NotifyAdapter(getApplicationContext(), datumList);
                myrecyclerView4.setAdapter(recyclerviewAdapter1);
            }

            @Override
            public void onFailure(Call<Notify_Example> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notify_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.notifyback_menuID4:
                Intent intent = new Intent(NotifyActivity.this,DashboardActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
