package com.example.donorin;

import static com.example.donorin.utama.storeNik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class listRiwayat extends AppCompatActivity {

    RecyclerView rvRiwayat;
    TextView btnRspmi, btnPengaturan, btnUtama;

    private static final String PREFS_NAME = "YourPrefsFile";
    private static final String KEY_NIK = "nik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        rvRiwayat = findViewById(R.id.rvRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);
        btnPengaturan = findViewById(R.id.btnPengaturan);
        btnUtama = findViewById(R.id.btnUtama);

        String nik = getStoredNik2();

        // Jika nik belum tersimpan (nilai default), ambil dari intent
        if (nik.equals("default_value")) {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("nik")) {
                nik = intent.getStringExtra("nik");

                // Simpan nik ke SharedPreferences
                storeNik(listRiwayat.this, nik);
            }
        }
        getRiwayat(nik);

        btnRspmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRiwayat.this, listRspmi.class);

                startActivity(intentPindah);
            }
        });

        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRiwayat.this, profil.class);

                startActivity(intentPindah);
            }
        });

        btnUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRiwayat.this, utama.class);

                startActivity(intentPindah);
            }
        });
    }

    private String getStoredNik2() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getString(KEY_NIK, "default_value");
    }

    private void getRiwayat(String nik) {
        Call<List<riwayatData>> apiCall =  RetroServer.getRetrofitAPI().getRiwayat(nik);
        apiCall.enqueue(new Callback<List<riwayatData>>() {
            @Override
            public void onResponse(Call<List<riwayatData>> call, Response<List<riwayatData>> response) {
                List<riwayatData> riwayatDataList = response.body();
                Toast.makeText(listRiwayat.this, "Riwayat terambil", Toast.LENGTH_SHORT).show();
                setAdapter(riwayatDataList);
            }

            @Override
            public void onFailure(Call<List<riwayatData>> call, Throwable t) {
                Toast.makeText(listRiwayat.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<riwayatData> riwayatDataList) {
        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));
        riwayatAdapter riwayatAdapter = new riwayatAdapter(this, riwayatDataList);
        rvRiwayat.setAdapter(riwayatAdapter);
    }
}