package com.example.donorin;
import com.example.donorin.DataModalRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("register")
    Call<DataModalRegister> createPost(@Body DataModalRegister dataModal);

    @POST("login")
    Call<DataModalLogin> createPostLogin(@Body DataModalLogin dataModalLogin);

    @POST("forgot")
    Call<DataModalForgot> createPostForgot(@Body DataModalForgot dataModalForgot);

    @POST("daftar")
    Call<DataModalDaftar> createPostDaftar(@Body DataModalDaftar dataModalDaftar);
}
