package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;

public class lupapassword extends AppCompatActivity {

    private EditText inputUsername, inputnew_password;
    private TextView txtLogin;
    private Button btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapassword);

        inputUsername = findViewById(R.id.inputUsername);
        inputnew_password = findViewById(R.id.inputNewPassword);
        txtLogin = findViewById(R.id.txtLogin);
        btnForgot = findViewById(R.id.btnForgot);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (inputUsername.getText().toString().trim().length()==0
                        || inputnew_password.getText().toString().trim().length()==0) {
                    Toast.makeText(lupapassword.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postDataForgot(inputUsername.getText().toString(),
                            inputnew_password.getText().toString());
                }
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang akan diambil saat TextView (txtForgot) diklik
                // Misalnya, pindah ke halaman baru menggunakan Intent

                Intent intent = new Intent(lupapassword.this, login.class);
                // Gantilah NamaActivitySaatIni dan NamaActivityTujuan dengan nama aktivitas sebenarnya

                // Jika Anda ingin membawa data tambahan ke aktivitas tujuan, Anda dapat menggunakan putExtra
                // intent.putExtra("key", "value");

                startActivity(intent);
            }
        });


    }

    private void postDataForgot(String username, String new_password) {

        // below line is for displaying our progress bar.
//        loadingPB.setVisibility(View.VISIBLE);

        // passing data from our text fields to our modal class.
        DataModalForgot modal = new DataModalForgot(username, new_password);

        // calling a method to create a post and passing our modal class.
        Call<DataModalForgot> call = RetroServer.getRetrofitAPI().createPostForgot(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalForgot>() {
            @Override
            public void onResponse(Call<DataModalForgot> call, Response<DataModalForgot> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DataModalForgot responseFromAPI = response.body();

                    // Assuming there is a "status" field in the response indicating success
                    if (responseFromAPI.getStatus().equals("success")) {
                        Toast.makeText(lupapassword.this, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show();
                        inputUsername.setText("");
                        inputnew_password.setText("");
                        Intent intent = new Intent(lupapassword.this, login.class);
                        startActivity(intent);
                        finish(); //
                    } else {
                        Toast.makeText(lupapassword.this, "Gagal Login / Username Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response (HTTP error, network issues, etc.)
                    Toast.makeText(lupapassword.this, "Gagal Login / Username Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                }
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