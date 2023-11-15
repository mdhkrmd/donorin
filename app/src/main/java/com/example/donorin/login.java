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

public class login extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (inputUsername.getText().toString().trim().length()==0
                        || inputPassword.getText().toString().trim().length()==0) {
                    Toast.makeText(login.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postDataLogin(inputUsername.getText().toString(),
                            inputPassword.getText().toString());
                }
            }
        });
    }
    private void postDataLogin(String username, String password) {

        // below line is for displaying our progress bar.
//        loadingPB.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://7d19-125-164-21-248.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        DataModalLogin modal = new DataModalLogin(username, password);

        Call<DataModalLogin> call = retrofitAPI.createPostLogin(modal);

        call.enqueue(new Callback<DataModalLogin>() {
            @Override
            public void onResponse(Call<DataModalLogin> call, Response<DataModalLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DataModalLogin responseFromAPI = response.body();

                    // Assuming there is a "status" field in the response indicating success
                    if (responseFromAPI.getStatus().equals("success")) {
                        Toast.makeText(login.this, "Berhasil Login", Toast.LENGTH_SHORT).show();

                        inputUsername.setText("");
                        inputPassword.setText("");

                        String kirimusername = responseFromAPI.getUsername();
                        String nik = responseFromAPI.getNik();
                        String nama = responseFromAPI.getNama();
                        String lahir = responseFromAPI.getLahir();
                        String goldar = responseFromAPI.getDarah();
                        String alamat = responseFromAPI.getAlamat();
                        String no = responseFromAPI.getNohp();
                        int poin = responseFromAPI.getPoin();

                        // Assuming Utama.class is the target activity
                        Intent intent = new Intent(login.this, utama.class);

                        intent.putExtra("username", kirimusername);
                        intent.putExtra("nik", nik);
                        intent.putExtra("nama", nama);
                        intent.putExtra("lahir", lahir);
                        intent.putExtra("goldar", goldar);
                        intent.putExtra("alamat", alamat);
                        intent.putExtra("no", no);
                        intent.putExtra("poin", poin);

                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response (HTTP error, network issues, etc.)
                    Toast.makeText(login.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModalLogin> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}