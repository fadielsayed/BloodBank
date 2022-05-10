package com.example.fadii.bloodbank.registerPK;


import com.example.fadii.bloodbank.MAIN.ArticlesPK.ArticleUser;
import com.example.fadii.bloodbank.MAIN.DonatesPK.RquestDonatUser;
import com.example.fadii.bloodbank.mainUIPK.Article_Detail.PostsDetails_Example;
import com.example.fadii.bloodbank.mainUIPK.DonateRequestDetail.DonateRequest_Example;
import com.example.fadii.bloodbank.mainUIPK.DonationDetail.Donator_Example;
import com.example.fadii.bloodbank.mainUIPK.NotifyPK.Notify_Example;
import com.example.fadii.bloodbank.navigationScreens.CallUsPK.CallUsExample;
import com.example.fadii.bloodbank.navigationScreens.favoritePk.FavouriteExample;
import com.example.fadii.bloodbank.navigationScreens.favoritePk.NotificationCount.NotificationExample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("governorates")
    Call<Governetmodel> getListofgouvernet();

    @FormUrlEncoded
    @POST("register")
    Call<User> creatUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("birth_date") String birth_date,
            @Field("city_id") int city_id,
            @Field("phone") String phone,
            @Field("donation_last_date") String donation_date,
            @Field("password") String password,
            @Field("password_confirmation") String confirm_password,
            @Field("blood_type") String blood_type
    );

    @GET("cities")
    Call<cityModel> Getlistofcity(@Query("governorate_id") int id);

    @FormUrlEncoded
    @POST("login")
    Call<User> userLogin(
            @Field("phone") String phone,
            @Field("password") String password
    );

    @GET("posts")
    Call<ArticleUser> getArticles(@Query("api_token") String id);

    @GET("donation-requests")
    Call<RquestDonatUser> getReqDonation(@Query("api_token") String id);

    @GET("post")
    Call<PostsDetails_Example> getPost_details(@Query("api_token") String api, @Query("post_id") String post_id);


    @FormUrlEncoded
    @POST("donation-request/create")
    Call<DonateRequest_Example> postData(
            @Field("patient_name") String patient_name,
            @Field("patient_age") String patient_age,
            @Field("blood_type") String blood_type,
            @Field("bags_num") String bags_num,
            @Field("hospital_name") String hospital_name,
            @Field("hospital_address") String hospital_address,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("city_id") String city_id,
            @Field("phone") String phone,
            @Field("notes") String notes
    );

    @GET("donation-request")
    Call<Donator_Example> getData(@Query("api_token") String api
            , @Query("donation_id") String id
    );

    @GET("settings")
    Call<CallUsExample> getSetting(@Query("api_token") String api);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<ArticleUser> addFavourite(@Field("post_id") int id, @Field("api_token") String api_token);

    @GET("my-favourites")
    Call<FavouriteExample> getFavoutite(@Query("api_token") String api);

    @GET("notifications")
    Call<Notify_Example> getNotify(@Query("api_token") String api);

    @GET("notifications-count")
    Call<NotificationExample> getCount(@Query("api_token") String api);


}