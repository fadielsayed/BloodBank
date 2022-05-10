package com.example.fadii.bloodbank.mainUIPK.DonateRequestDetail;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fadii.bloodbank.navigationScreens.DashboardActivity;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.DataCity;
import com.example.fadii.bloodbank.registerPK.Dataitem;
import com.example.fadii.bloodbank.registerPK.Governetmodel;
import com.example.fadii.bloodbank.registerPK.Rertrofitinstance;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;
import com.example.fadii.bloodbank.registerPK.cityModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationrequestActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    Spinner spinner2, cityspinner2;
    Button sendRequestButton , getadresseButton;

    private   EditText gouvertnetTxt , citytxt;
    private EditText editextName, editTextAge, editTextBloodkind, editTextBagsnumber, editTextHospital,
            editTextHospitalAdresse, editTextcity , editTextphone, editTextnotes,editTexthospitalatangtude;

    //place picker code

    private final String TAG = "PLACEPICKER_EXERCISE";

    private final int RESOLVE_CONNECTION_REQUEST_CODE = 1000;
    private final int PLACE_PICKER_REQUEST = 1001;

    protected GoogleApiClient mGoogleApiClient;
    private boolean mHaveLocPerm = false;


    // Invoked when the user clicks on the "Trigger the PlacePicker"
    // button in the main activity
    private void pickAPlace() {
        if (mHaveLocPerm) {
            try {
                // TODO: set a default viewport
                // get the bounding area for the Pike Place market
                LatLngBounds bounds = new LatLngBounds.Builder()
                        .include(new LatLng(47.608670, -122.340033))
                        .include(new LatLng(47.610005, -122.342865))
                        .build();

                // Trigger the PlacePicker, which will then pass the result
                // to the onActivityResult function below
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent = builder
                        .setLatLngBounds(bounds)
                        .build(this);

                startActivityForResult(intent, PLACE_PICKER_REQUEST);
            }
            catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }


    // Called from onActivityResult to update the UI with the data
    // for the Place that was selected by the user
    private void updateUI(Place chosenPlace) {
        EditText placeData = (EditText) findViewById(R.id.address2);

        String str =  chosenPlace.getLatLng().toString();

        placeData.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationrequest);


        //place piker code
        // Build the GoogleApiClient and connect to the Places API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        findViewById(R.id.hospitaladresseBT).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickAPlace();
                    }
                }
        );



        Toolbar toolbar = findViewById(R.id.donrequesttoolbar_ID);
        setSupportActionBar(toolbar);
        spinner2 = findViewById(R.id.spinnerCountry2);
        cityspinner2 = findViewById(R.id.spinnerCity2);
        gouvertnetTxt = findViewById(R.id.gouvernetID);
        citytxt = findViewById(R.id.citytxtID);
        getListofgouvernet();

//        SendRequest();
        editTexthospitalatangtude = findViewById(R.id.address2);
        getadresseButton = findViewById(R.id.hospitaladresseBT);
        editextName = findViewById(R.id.name);
        editTextAge = findViewById(R.id.birth);
        editTextBloodkind = findViewById(R.id.blood_kind);
        editTextBagsnumber = findViewById(R.id.aquasText);
        editTextHospital = findViewById(R.id.hospitalnametxt);
        editTextHospitalAdresse  = findViewById(R.id.address2);
        editTextcity = findViewById(R.id.citytxtID);
        editTextphone = findViewById(R.id.phonetxt);
        editTextnotes = findViewById(R.id.notice);
        sendRequestButton = findViewById(R.id.sendRequesBT);
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendRequest();
            }
        });

    }

    private void SendRequest() {

        String name = editextName.getText().toString();
        String age = editTextAge.getText().toString();
        String blood = editTextBloodkind.getText().toString();
        String aquas = editTextBagsnumber.getText().toString();
        String hospital = editTextHospital.getText().toString();
        String hospitalAdresse = editTextHospitalAdresse.getText().toString();
        String city = editTextcity.getText().toString();
        String phone = editTextphone.getText().toString();
        String notes = editTextnotes.getText().toString();
        String latangtude = editTexthospitalatangtude.getText().toString();
        String latitude = editTexthospitalatangtude.getText().toString();




        Call<DonateRequest_Example> call = RetrofitClient.getInstance().getApi()
               .postData(name,age,blood,aquas,hospital,hospitalAdresse,latangtude,latitude,city,phone,notes);
       call.enqueue(new Callback<DonateRequest_Example>() {
           @Override
           public void onResponse(Call<DonateRequest_Example> call, Response<DonateRequest_Example> response) {
               if (response.body().getStatus() == 1) {
                   DonateRequest_Example dr = response.body();
                   Toast.makeText(DonationrequestActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                   Intent i = new Intent(DonationrequestActivity.this, DashboardActivity.class);
                   startActivity(i);
                   Log.e("myResponse", "1"+response.body().getMsg());

               } else {
                   Log.e("myResponse", response.body().getMsg());

               }
           }


           @Override
           public void onFailure(Call<DonateRequest_Example> call, Throwable t) {

               Log.e("myResponse", "error:  " + t.getMessage());

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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DonationrequestActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                spinner2.setAdapter(adapter);

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                        if (position != 0) {
                            id[0] = listIds.get(position - 1);
                            getlistofcity(id[0]);
                            gouvertnetTxt.setText(listSpinner.get(position));

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
                for (int i = 0; i < dataCity.size(); i++) {
                    listSpinner.add(dataCity.get(i).getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DonationrequestActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                cityspinner2.setAdapter(adapter);
                cityspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        citytxt.setText(listSpinner.get(position));


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donatorrequest_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.donatorback_menuID:
                Intent intent = new Intent(DonationrequestActivity.this,DashboardActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //place piker code
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            // This code is passed when the user has resolved whatever connection
            // problem there was with the Google Play Services library
            case RESOLVE_CONNECTION_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mGoogleApiClient.connect();
                }
                break;

            case PLACE_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Place chosenPlace = PlacePicker.getPlace(this, data);
                    updateUI(chosenPlace);
                }
                break;
        }
    }

    /**
     * Called when the user has been prompted at runtime to grant permissions
     */
    @Override
    public void onRequestPermissionsResult(int reqCode, String[] perms, int[] results){
        if (reqCode == 1) {
            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                mHaveLocPerm = true;
            }
        }
    }

    /**
     * Standard Google Play Services lifecycle callbacks
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "Connected to Places API");

        // If we're running on API 23 or above, we need to ask permissions at runtime
        // In this case, we need Fine Location access to use Place Detection
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            mHaveLocPerm = true;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
            }
            catch (IntentSender.SendIntentException e) {
                // Unable to resolve, message user appropriately
            }
        }
        else {
            GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
            gAPI.getErrorDialog(this, result.getErrorCode(), 0).show();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Connection was suspended for some reason");
        mGoogleApiClient.connect();
    }



}
