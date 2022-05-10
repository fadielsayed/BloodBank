package com.example.fadii.bloodbank.MAIN.ArticlesPK;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.registerPK.Api;
import com.example.fadii.bloodbank.registerPK.Dataitem;
import com.example.fadii.bloodbank.registerPK.Rertrofitinstance;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;
import com.example.fadii.bloodbank.registerPK.SignupActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class articalsFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerView;
    private List<ArticleData> lstarticles;
    private Spinner searchSpinner;

    public articalsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.article_fragment,container,false);
        myrecyclerView = v.findViewById(R.id.article_recycler);
        searchSpinner = v.findViewById(R.id.articleSpinner_ID);
        getArticles();
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final Api dataservices = Rertrofitinstance.getservice();
        Call<ArticleUser> call = dataservices.getArticles("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<ArticleUser>() {
            @Override
            public void onResponse(Call<ArticleUser> call, Response<ArticleUser> response) {
                List<ArticleDatum> datumList = response.body().getData().getData();
              RecyclerviewAdapter1 recyclerviewAdapter1 = new RecyclerviewAdapter1(getContext(),datumList);
              myrecyclerView.setAdapter(recyclerviewAdapter1);
            }
            @Override
            public void onFailure(Call<ArticleUser> call, Throwable t) {

            }
        });
return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void getArticles(){
    Call<ArticleUser> call = RetrofitClient.getInstance()
            .getApi().getArticles("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<ArticleUser>() {
            @Override
            public void onResponse(Call<ArticleUser> call, Response<ArticleUser> response) {

                ArticleUser articleUser = response.body();
                List<ArticleDatum> dataitems = articleUser.getData().getData();
                final List<String> listSpinner = new ArrayList<>();
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < dataitems.size(); i++) {
                    listSpinner.add(dataitems.get(i).getArticleCategory().getName());
                    listIds.add(dataitems.get(i).getId());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listSpinner);
                searchSpinner.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArticleUser> call, Throwable t) {

            }
        });

    }

}
