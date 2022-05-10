package com.example.fadii.bloodbank.mainUIPK.Article_Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fadii.bloodbank.MAIN.ArticlesPK.ArticleDatum;
import com.example.fadii.bloodbank.MAIN.ArticlesPK.ArticleUser;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.fadii.bloodbank.registerPK.RetrofitClient.getInstance;

public class DiseasepreventActivity extends AppCompatActivity {
    ImageView interface_image;
    TextView title;
    TextView details;
    List<ArticleDatum> mdata;
    CheckBox favDetailBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseaseprevent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dieasespretoolbar_ID);
        setSupportActionBar(toolbar);
        interface_image =  findViewById(R.id.Image_detailID);
        title = findViewById(R.id.title_ID);
        details = findViewById(R.id.descriptionID);
        favDetailBT = findViewById(R.id.favDetailID);
        getData();

    }


    public void getData(){
        final Api dataservices = RetrofitClient.getInstance().getApi();
        Call<PostsDetails_Example> call = dataservices.getPost_details("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27","1");
        call.enqueue(new Callback<PostsDetails_Example>() {
            @Override
            public void onResponse(Call<PostsDetails_Example> call, Response<PostsDetails_Example> response) {
                PostsDetails_Example example = response.body();
                PostsDetails_Data postsDetailsCategory= example.getData();
                title.setText(postsDetailsCategory.getTitle());
                details.setText(postsDetailsCategory.getContent());
                Glide.with(getApplicationContext()).load(postsDetailsCategory.getThumbnailFullPath()).into(interface_image);

            }

            @Override
            public void onFailure(Call<PostsDetails_Example> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.diseaseprev_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back_menuID:
                Intent intent = new Intent(DiseasepreventActivity.this,DashboardActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
