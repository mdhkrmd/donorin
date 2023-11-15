package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;

public class lupapassword extends AppCompatActivity {

    private EditText inputUsername, inputNewPassword;
    private Button btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapassword);

        inputUsername = findViewById(R.id.inputUsername);
        inputNewPassword = findViewById(R.id.inputNewPassword);
        btnForgot = findViewById(R.id.btnForgot);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (inputUsername.getText().toString().trim().length()==0
                        || inputNewPassword.getText().toString().trim().length()==0) {
                    Toast.makeText(lupapassword.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postDataForgot(inputUsername.getText().toString(),
                            inputNewPassword.getText().toString());
                }
            }
        });
    }

    private void postDataForgot(String username, String newpassword) {

        // below line is for displaying our progress bar.
//        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://b18f-103-23-244-254.ngrok.io/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModalForgot modal = new DataModalForgot(username, newpassword);

        // calling a method to create a post and passing our modal class.
        Call<DataModalForgot> call = retrofitAPI.createPostForgot(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalForgot>() {
            @Override
            public void onResponse(Call<DataModalForgot> call, Response<DataModalForgot> response) {
                // this method is called when we get response from our api.
                Toast.makeText(lupapassword.this, "Ganti Password Berhasil", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
//                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                inputUsername.setText("");
                inputNewPassword.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModalForgot responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code();

                // below line we are setting our
                // string to our text view.
//                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModalForgot> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }

}