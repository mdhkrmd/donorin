package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class login extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button btnLogin;
    private CheckBox chkBox;
    private TextView txtForgot, txtDaftar;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtForgot = findViewById(R.id.txtLupa);
        txtDaftar = findViewById(R.id.tvDaftar);
        chkBox = findViewById(R.id.checkBoxIngat);

        // Mendapatkan preferensi login
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        // Mengatur nilai checkbox sesuai preferensi
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        chkBox.setChecked(saveLogin);

        // Jika checkbox tercentang, lakukan auto-login
        if (saveLogin) {
            String savedUsername = loginPreferences.getString("username", "");
            String savedPassword = loginPreferences.getString("password", "");
            postDataLogin(savedUsername, savedPassword);
        }


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

        // Dalam metode onCreate atau metode lainnya, setelah inisialisasi txtForgot
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang akan diambil saat TextView (txtForgot) diklik
                // Misalnya, pindah ke halaman baru menggunakan Intent

                Intent intent = new Intent(login.this, lupapassword.class);
                // Gantilah NamaActivitySaatIni dan NamaActivityTujuan dengan nama aktivitas sebenarnya

                // Jika Anda ingin membawa data tambahan ke aktivitas tujuan, Anda dapat menggunakan putExtra
                // intent.putExtra("key", "value");

                startActivity(intent);
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang akan diambil saat TextView (txtForgot) diklik
                // Misalnya, pindah ke halaman baru menggunakan Intent

                Intent intent = new Intent(login.this, MainActivity.class);
                // Gantilah NamaActivitySaatIni dan NamaActivityTujuan dengan nama aktivitas sebenarnya

                // Jika Anda ingin membawa data tambahan ke aktivitas tujuan, Anda dapat menggunakan putExtra
                // intent.putExtra("key", "value");

                startActivity(intent);
            }
        });

        chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Menyimpan status checkbox ke preferensi
                loginPrefsEditor.putBoolean("saveLogin", isChecked);
                loginPrefsEditor.apply();
            }
        });
    }

    public static void clearLoginPreferences(Context context) {
        SharedPreferences loginPreferences = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor loginPrefsEditor = loginPreferences.edit();
        loginPrefsEditor.clear();
        loginPrefsEditor.apply();
    }

    private void postDataLogin(String username, String password) {

        DataModalLogin modal = new DataModalLogin(username, password);
        Call<DataModalLogin> call = RetroServer.getRetrofitAPI().createPostLogin(modal);

        if (chkBox.isChecked()) {
            loginPrefsEditor.putString("username", username);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.apply();
        }
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

//                        String kirimusername = responseFromAPI.getUsername();
                        String nik = responseFromAPI.getNik();
                        String nama = responseFromAPI.getNama();
                        String lahir = responseFromAPI.getLahir();
                        String goldar = responseFromAPI.getDarah();
                        String alamat = responseFromAPI.getAlamat();
                        String no = responseFromAPI.getNohp();
                        int poin = responseFromAPI.getPoin();

                        // Assuming Utama.class is the target activity
                        Intent intent = new Intent(login.this, utama.class);

                        intent.putExtra("username", inputUsername.getText().toString());
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