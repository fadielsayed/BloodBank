package com.example.fadii.bloodbank.registerPK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.navigationScreens.NavigationDrawerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginBT;
    Button signuoBT;
    private EditText editTextPHONE;
    private EditText editTextPASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPHONE = findViewById(R.id.edittextPHONE);
        editTextPASSWORD = findViewById(R.id.edittextPASSWORD);

        loginBT = findViewById(R.id.loginButton_ID);
        signuoBT = findViewById(R.id.signupButton);
        signuoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }

            private void userLogin() {
                String phone = editTextPHONE.getText().toString().trim();
                String password = editTextPASSWORD.getText().toString().trim();

                if (phone.isEmpty()) {
                    editTextPHONE.setError("phone is required");
                    editTextPHONE.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTextPASSWORD.setError("password is required");
                    editTextPASSWORD.requestFocus();
                    return;

                }

                Call<User> call = RetrofitClient.getInstance()
                        .getApi().userLogin(phone,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User userlogin = response.body();
                        if (!userlogin.getStatus().equals(0)){
                            Toast.makeText(LoginActivity.this,userlogin.getMsg(),Toast.LENGTH_LONG).show();

                            SharedprefManager.getInstance(LoginActivity.this)
                                    .saveUser(userlogin.getData().getClient());
                            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }else {
                            Toast.makeText(LoginActivity.this,userlogin.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

                }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedprefManager.getInstance(this).isloggedIn()){
            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}
