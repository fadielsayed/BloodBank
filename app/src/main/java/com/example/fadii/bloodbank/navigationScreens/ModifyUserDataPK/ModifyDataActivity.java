package com.example.fadii.bloodbank.navigationScreens.ModifyUserDataPK;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.fadii.bloodbank.R;

public class ModifyDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.modifytoolbar_ID);
        setSupportActionBar(toolbar);



    }



}
