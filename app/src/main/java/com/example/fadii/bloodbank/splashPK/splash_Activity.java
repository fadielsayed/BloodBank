package com.example.fadii.bloodbank.splashPK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fadii.bloodbank.viewpagerPK.MainActivity;
import com.example.fadii.bloodbank.R;

public class splash_Activity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        imageView = findViewById(R.id.logoimg_VIEW);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.setAnimation(myanim);
          final Intent i = new Intent(this,MainActivity.class);

        Thread timer = new Thread(){

            public void run (){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();


    }
}
