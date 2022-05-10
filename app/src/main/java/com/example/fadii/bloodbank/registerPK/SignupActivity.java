package com.example.fadii.bloodbank.registerPK;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    Spinner spinner, cityspinner;
    Button signUpButton;
    private EditText editextName, editTextEmail, editTextBirthday, editTextBlood, editTextPhone,
            editTextPassword, editTextConfirmPass, editTextLastDonation ,editTextCity,editTextGouvernet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.signuptoolbar_ID);
        setSupportActionBar(toolbar);
        getListofgouvernet();

        spinner = findViewById(R.id.gouvernet_spinner);
        cityspinner = findViewById(R.id.cityspinner);


        editextName = findViewById(R.id.editTextname);
        editTextBirthday = findViewById(R.id.editTextbirthday);
        editTextEmail = findViewById(R.id.editTextemail);
        editTextBlood = findViewById(R.id.editTextblood);
        editTextPhone = findViewById(R.id.editTextphone);
        editTextPassword = findViewById(R.id.editTextpassword);
        editTextConfirmPass = findViewById(R.id.editTextconfirmpass);
        editTextLastDonation = findViewById(R.id.editTextlastdonation);
        editTextGouvernet = findViewById(R.id.gouvernetTxt);
        editTextCity = findViewById(R.id.cityTxt);
        signUpButton = findViewById(R.id.signupButton2);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedprefManager.getInstance(this).isloggedIn()){
            Intent intent = new Intent(SignupActivity.this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    private void userSignup() {
        final String name = editextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String birthday = editTextBirthday.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String blood = editTextBlood.getText().toString().trim();
        String confirmpass = editTextConfirmPass.getText().toString().trim();
        String lastdonation = editTextLastDonation.getText().toString().trim();
        int city_id = editTextCity.getId();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editextName.setError("name is required");
            editextName.requestFocus();
            return;
        }




        Call<User> call = RetrofitClient.getInstance()
                .getApi().
                        creatUser(name,email,birthday,city_id,phone,lastdonation,password,confirmpass,blood);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String s = response.body().getMsg();
                Toast.makeText(SignupActivity.this, response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(SignupActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getListofgouvernet() {
        final Api dataservices = Rertrofitinstance.getservice();
        Call<Governetmodel> call = dataservices.getListofgouvernet();
        call.enqueue(new Callback<Governetmodel>() {
            @Override
            public void onResponse(Call<Governetmodel> call, Response<Governetmodel> response) {
                Governetmodel governetmodel = response.body();
                List<Dataitem> dataitems = governetmodel.getData();
                final List<String> listSpinner = new ArrayList<>();
                Log.i("gouvernetmodel", "" + dataitems.size());
                listSpinner.add("اختر المحافظة");
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < dataitems.size(); i++) {
                    listSpinner.add(dataitems.get(i).getName());
                    listIds.add(dataitems.get(i).getId());

                }
                final int[] id = {-1};

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                        if (position != 0) {
                            id[0] = listIds.get(position - 1);
                            getlistofcity(id[0]);
                            editTextGouvernet.setText(listSpinner.get(position));

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Governetmodel> call, Throwable t) {
                Log.i("governetmodel", "error");
            }
        });
    }

    private void getlistofcity(int gov_id) {
        final Api dataservices = Rertrofitinstance.getservice();
        Call<cityModel> call = dataservices.Getlistofcity(gov_id);
        Log.i("selectedItem","getCity");

        call.enqueue(new Callback<cityModel>() {
            @Override
            public void onResponse(Call<cityModel> call, Response<cityModel> response) {
                cityModel cityModel = response.body();
                List<DataCity> dataCity = cityModel.getData();

                final List<String> listSpinner = new ArrayList<>();
                listSpinner.add("اختر المدينه");
                Log.i("selectedItem","getCity response");
                for (int i = 0; i < dataCity.size(); i++) {
                    listSpinner.add(dataCity.get(i).getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                cityspinner.setAdapter(adapter);
                cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("selectedItem", listSpinner.get(position));
                        editTextCity.setText(listSpinner.get(position));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<cityModel> call, Throwable t) {
                Log.i("selectedItem","getCity error");

            }
        });


    }

}
