package com.example.fadii.bloodbank.navigationScreens.CallUsPK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallusActivity extends AppCompatActivity {

    private TextView  userphone , useremail;
    private ImageView facebook,twiter,youtube,googleplus,instagram,whatsaap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.callustoolbar_ID);
        setSupportActionBar(toolbar);

        userphone = findViewById(R.id.phoneTextID);
        useremail = findViewById(R.id.emailtextID);
        facebook = findViewById(R.id.facebook);
        twiter = findViewById(R.id.twiter);
        youtube = findViewById(R.id.youtub);
        googleplus = findViewById(R.id.googleplus);
        instagram = findViewById(R.id.instagram);
        whatsaap = findViewById(R.id.whatsapp);
        getSetting();

    }

    public void getSetting (){

        Call<CallUsExample> call = RetrofitClient.getInstance().getApi()
                .getSetting("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<CallUsExample>() {
            @Override
            public void onResponse(Call<CallUsExample> call, Response<CallUsExample> response) {

                final CallusData data = response.body().getData();
                userphone.setText(data.getPhone());
                useremail.setText(data.getEmail());
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getFacebookUrl();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                twiter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getTwitterUrl();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getYoutubeUrl();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                instagram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getInstagramUrl();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                googleplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getGoogleUrl();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                whatsaap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = data.getWhatsapp();

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });





            }

            @Override
            public void onFailure(Call<CallUsExample> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.callus_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.callusback_menuID:
                Intent intent = new Intent(CallusActivity.this,DashboardActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
