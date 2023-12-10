package com.example.donorin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static final String BASE_URL = "https://cf9d-125-164-22-219.ngrok-free.app/";

    public static RetrofitAPI getRetrofitAPI() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitAPI.class);
    }
}