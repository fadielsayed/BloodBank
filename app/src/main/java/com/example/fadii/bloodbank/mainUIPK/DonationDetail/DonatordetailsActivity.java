package com.example.fadii.bloodbank.mainUIPK.DonationDetail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;
import com.example.fadii.bloodbank.registerPK.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonatordetailsActivity extends AppCompatActivity {
    private TextView donatorName, donatorAge, donatorBloodkind, donatorBloodBags, donatorHospital,
            donatorhospitalAdresse, donatorPhone, donatorDetail, headerText;
    private ImageView donatorImage;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donatordetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dondetailtoolbar_ID);
        setSupportActionBar(toolbar);
        headerText = findViewById(R.id.headerText);
        donatorName = findViewById(R.id.donatorname);
        donatorAge = findViewById(R.id.donatorage);
        donatorBloodkind = findViewById(R.id.donatorbloodkind);
        donatorBloodBags = findViewById(R.id.donatorbloodBags);
        donatorHospital = findViewById(R.id.donatorhospital);
        donatorhospitalAdresse = findViewById(R.id.donatorhospitalAdresse);
        donatorDetail = findViewById(R.id.donatordetail);
        donatorPhone = findViewById(R.id.donatorphone);
        donatorImage = findViewById(R.id.donatorimageID);
        mButton = findViewById(R.id.donatorbutton);
        getData();
    }

    public void getData() {

        Call<Donator_Example> call = RetrofitClient.getInstance().getApi().
                getData("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", "1");

        call.enqueue(new Callback<Donator_Example>() {
            @Override
            public void onResponse(Call<Donator_Example> call, Response<Donator_Example> response) {
                Donator_Example example = response.body();
                final Donator_Data postsDetailsCategory = example.getData();
                donatorBloodkind.setText(postsDetailsCategory.getBloodType());
                donatorName.setText(postsDetailsCategory.getPatientName());
                donatorAge.setText(postsDetailsCategory.getPatientAge());
                donatorBloodBags.setText(postsDetailsCategory.getBagsNum());
                donatorHospital.setText(postsDetailsCategory.getHospitalName());
                donatorhospitalAdresse.setText(postsDetailsCategory.getHospitalAddress());
                donatorPhone.setText(postsDetailsCategory.getPhone());
                donatorDetail.setText(postsDetailsCategory.getNotes());
                headerText.setText(postsDetailsCategory.getPatientName());


                Log.e("myResponse", "1" + response.body().getData());


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone = postsDetailsCategory.getPhone();

                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:" + phone));
                        if (ActivityCompat.checkSelfPermission(DonatordetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermission();
                        } else {
                            startActivity(i);
                        }
                    }
                });

            }

            @Override
            public void onFailure(retrofit2.Call<Donator_Example> call, Throwable t) {

            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(DonatordetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donator_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.detailback_menuID:
                Intent intent = new Intent(DonatordetailsActivity.this, DashboardActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
