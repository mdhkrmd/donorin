package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class riwayat extends AppCompatActivity {

    RecyclerView rvRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        rvRiwayat = findViewById(R.id.rvRiwayat);

//        getRiwayat();
    }
//
//    private void getRiwayat() {
//        Call<List<RspmiData>> apiCall =  RetroServer.getRetrofitAPI().getData();
//        apiCall.enqueue(new Callback<List<RspmiData>>() {
//            @Override
//            public void onResponse(Call<List<RspmiData>> call, Response<List<RspmiData>> response) {
//                List<RspmiData> rspmiDataList = response.body();
//                Toast.makeText(riwayat.this, "RSPMI terambil", Toast.LENGTH_SHORT).show();
//                setAdapter(rspmiDataList);
//            }
//
//            @Override
//            public void onFailure(Call<List<RspmiData>> call, Throwable t) {
//                Toast.makeText(riwayat.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void setAdapter(List<RspmiData> rspmiDataList) {
//        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));
//        RspmiAdapter rspmiAdapter = new RspmiAdapter(this, rspmiDataList);
//        rvRiwayat.setAdapter(rspmiAdapter);
//    }
}