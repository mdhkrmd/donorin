package com.example.donorin;

import static com.example.donorin.utama.storeNik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profil extends AppCompatActivity {

    TextView ambilNama, txtUpdate, txtLogout, ambilNik, ambilDarah, ambilAlamat, ambilLahir, ambilNo, btnUtama2, btnRiwayat, btnRspmi;

    private static final String PREFS_NAME = "YourPrefsFile";
    private static final String KEY_NIK = "nik";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ambilNama = findViewById(R.id.ambilNama);
        ambilNik = findViewById(R.id.ambilNik);
        ambilLahir = findViewById(R.id.ambilLahir);
        ambilDarah = findViewById(R.id.ambilDarah);
        ambilAlamat = findViewById(R.id.ambilAlamat);
        ambilNo = findViewById(R.id.ambilNo);
        btnUtama2 = findViewById(R.id.btnUtama2);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);


        txtUpdate = findViewById(R.id.txtUpdate);
        txtLogout = findViewById(R.id.txtLogout);

//        Intent intent = getIntent();
//        ambilNik.setText(intent.getStringExtra("nik"));
//        ambilNama.setText(intent.getExtras().getString("nama"));
//        ambilLahir.setText(intent.getExtras().getString("lahir"));
//        ambilDarah.setText(intent.getExtras().getString("darah"));
//        ambilAlamat.setText(intent.getExtras().getString("alamat"));
//        ambilNo.setText(intent.getExtras().getString("no"));

        String nik = getStoredNik3();

        // Jika nik belum tersimpan (nilai default), ambil dari intent
        if (nik.equals("default_value")) {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("nik")) {
                nik = intent.getStringExtra("nik");

                // Simpan nik ke SharedPreferences
                storeNik(profil.this, nik);
            }
        }

        getDataLogin(nik);

        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, updateProfil.class);
                intentPindah.putExtra("nik", ambilNik.getText().toString());
                intentPindah.putExtra("nama", ambilNama.getText().toString());
                intentPindah.putExtra("lahir", ambilLahir.getText().toString());
                intentPindah.putExtra("darah", ambilDarah.getText().toString());
                intentPindah.putExtra("alamat", ambilAlamat.getText().toString());
                intentPindah.putExtra("no", ambilNo.getText().toString());

                startActivity(intentPindah);
            }
        });

        btnUtama2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, utama.class);

                startActivity(intentPindah);
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, listRiwayat.class);

                startActivity(intentPindah);
            }
        });

        btnRspmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, listRspmi.class);

                startActivity(intentPindah);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeNik(profil.this, "default_value");
                login.clearLoginPreferences(profil.this);
                Intent intentPindah = new Intent(profil.this, login.class);
                startActivity(intentPindah);
                finish();
            }
        });
    }

    private String getStoredNik3() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getString(KEY_NIK, "default_value");
    }

    private void getDataLogin(final String nik) {
        Call<List<DataModalLogin>> apiCall = RetroServer.getRetrofitAPI().getLoginData(nik);
        apiCall.enqueue(new Callback<List<DataModalLogin>>() {
            @Override
            public void onResponse(Call<List<DataModalLogin>> call, Response<List<DataModalLogin>> response) {
                if (response.isSuccessful()) {
                    List<DataModalLogin> loginData = response.body();

                    // Check if the login data is not null
                    if (loginData != null && !loginData.isEmpty()) {
                        // Assuming getNama() is a method in DataModalLogin to get the name

                        String nik = loginData.get(0).getNik();
                        String nama = loginData.get(0).getNama();
                        String goldar = loginData.get(0).getDarah();
                        String alamat = loginData.get(0).getAlamat();
                        String no = loginData.get(0).getNohp();
                        String lahir = loginData.get(0).getLahir();

                        // Assuming ambilNama is a TextView

                        ambilNik.setText(nik);
                        ambilNama.setText(nama);
                        ambilDarah.setText(goldar);
                        ambilAlamat.setText(alamat);
                        ambilNo.setText(no);
                        ambilLahir.setText(lahir);

                        Toast.makeText(profil.this, "Data Login terambil", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(profil.this, "Login data is null or empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(profil.this, "Failed to get login data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModalLogin>> call, Throwable t) {
                Toast.makeText(profil.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}