package com.example.fadii.bloodbank.registerPK;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rertrofitinstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://ipda3.com/blood-bank/api/v1/";

    public static Api getservice (){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(Api.class);
    }
}
