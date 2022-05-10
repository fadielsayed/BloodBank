package com.example.fadii.bloodbank.MAIN.DonatesPK;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;


import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.DonationDetail.Donator_Example;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.Rertrofitinstance;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReqdonationFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerView2;
    private List<DonationDatum> lstDonates;
    Spinner citySearchspinner, bloodSearchspinner;
    ImageButton searchData;

    public ReqdonationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.requestdonation_fragment, container, false);
        getbloodspinner();
        getcityspinner();
        myrecyclerView2 = v.findViewById(R.id.reqdonation_recycler);
        citySearchspinner = v.findViewById(R.id.spinnerCity_ID);
        bloodSearchspinner = v.findViewById(R.id.spinnerbloodsearch_ID);
        searchData = v.findViewById(R.id.search_data);
        myrecyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        final Api dataservices = Rertrofitinstance.getservice();
        Call<RquestDonatUser> call = dataservices.getReqDonation("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<RquestDonatUser>() {
            @Override
            public void onResponse(Call<RquestDonatUser> call, Response<RquestDonatUser> response) {
                List<DonationDatum> datumList = response.body().getData().getData();
                RecyclerviewAdapter2 recyclerviewAdapter1 = new RecyclerviewAdapter2(getContext(), datumList);
                myrecyclerView2.setAdapter(recyclerviewAdapter1);
            }

            @Override
            public void onFailure(Call<RquestDonatUser> call, Throwable t) {
            }
        });
        return v;
    }



    public void getbloodspinner() {
        final Api dataservices = Rertrofitinstance.getservice();
        Call<RquestDonatUser> call = dataservices.getReqDonation("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<RquestDonatUser>() {
            @Override
            public void onResponse(Call<RquestDonatUser> call, Response<RquestDonatUser> response) {
                RquestDonatUser rquestDonatUser = response.body();
                List<DonationDatum> dataitems = rquestDonatUser.getData().getData();
                final List<String> listSpinner = new ArrayList<>();
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < dataitems.size(); i++) {
                    listSpinner.add(dataitems.get(i).getBloodType());
                    listIds.add(dataitems.get(i).getId());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listSpinner);
                bloodSearchspinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RquestDonatUser> call, Throwable t) {

            }
        });
    }

    public void getcityspinner() {
        final Api dataservices = Rertrofitinstance.getservice();
        Call<RquestDonatUser> call = dataservices.getReqDonation("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<RquestDonatUser>() {
            @Override
            public void onResponse(Call<RquestDonatUser> call, Response<RquestDonatUser> response) {
                RquestDonatUser rquestDonatUser = response.body();
                List<DonationDatum> dataitems = rquestDonatUser.getData().getData();
                final List<String> listSpinner = new ArrayList<>();
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < dataitems.size(); i++) {
                    listSpinner.add(dataitems.get(i).getCity().getName());
                    listIds.add(dataitems.get(i).getId());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listSpinner);
                citySearchspinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RquestDonatUser> call, Throwable t) {

            }
        });
    }

}
