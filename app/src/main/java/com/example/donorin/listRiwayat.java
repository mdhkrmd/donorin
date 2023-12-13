package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        rvRiwayat = findViewById(R.id.rvRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);
        btnPengaturan = findViewById(R.id.btnPengaturan);
        btnUtama = findViewById(R.id.btnUtama);

        Intent intent = getIntent();
        String nik = intent.getExtras().getString("nik");

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