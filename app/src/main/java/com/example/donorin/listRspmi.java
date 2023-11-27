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

public class listRspmi extends AppCompatActivity {

    RecyclerView rvRspmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rspmi);
        rvRspmi = findViewById(R.id.rvRspmi);

        getRspmi();
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