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

public class listRspmi extends AppCompatActivity {

    RecyclerView rvRspmi;

    TextView btnUtama1, btnRiwayat, btnPengaturan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rspmi);
        rvRspmi = findViewById(R.id.rvRspmi);
        btnUtama1 = findViewById(R.id.btnUtama1);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnPengaturan = findViewById(R.id.btnPengaturan);


        getRspmi();

        btnUtama1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRspmi.this, utama.class);

                startActivity(intentPindah);
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRspmi.this, listRiwayat.class);

                startActivity(intentPindah);
            }
        });

        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(listRspmi.this, profil.class);

                startActivity(intentPindah);
            }
        });
    }


    private void getRspmi() {
        Call<List<RspmiData>> apiCall =  RetroServer.getRetrofitAPI().getData();
        apiCall.enqueue(new Callback<List<RspmiData>>() {
            @Override
            public void onResponse(Call<List<RspmiData>> call, Response<List<RspmiData>> response) {
                List<RspmiData> rspmiDataList = response.body();
                Toast.makeText(listRspmi.this, "RSPMI terambil", Toast.LENGTH_SHORT).show();
                setAdapter(rspmiDataList);
            }

            @Override
            public void onFailure(Call<List<RspmiData>> call, Throwable t) {
                Toast.makeText(listRspmi.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<RspmiData> rspmiDataList) {
        rvRspmi.setLayoutManager(new LinearLayoutManager(this));
        RspmiAdapter rspmiAdapter = new RspmiAdapter(this, rspmiDataList);
        rvRspmi.setAdapter(rspmiAdapter);
    }
}