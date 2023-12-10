package com.example.donorin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static final String BASE_URL = "https://89fd-125-164-20-77.ngrok-free.app/";

    public static RetrofitAPI getRetrofitAPI() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitAPI.class);
    }
}