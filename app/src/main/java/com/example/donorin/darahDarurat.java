package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class darahDarurat extends AppCompatActivity {

    TextView btnUtama, btnRiwayat, btnRspmi, btnPengaturan;
    ListView lvDarahDarurat;
    DarahDaruratAdapter adapter;

    TextView ambilNik, ambilNama, ambilDarah, ambilAlamat, ambilNo;
    List<DataModalLogin> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darah_darurat);

        btnUtama = findViewById(R.id.btnUtama);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);
        btnPengaturan = findViewById(R.id.btnPengaturan);

        lvDarahDarurat = findViewById(R.id.lvDarahDarurat);

        ambilNik = findViewById(R.id.ambilNik);
        ambilNama = findViewById(R.id.ambilNama);
        ambilDarah = findViewById(R.id.ambilDarah);
        ambilAlamat = findViewById(R.id.ambilAlamat);
        ambilNo = findViewById(R.id.ambilNo);

        Intent intent = getIntent();
        ambilNik.setText(intent.getExtras().getString("nik"));
        ambilNama.setText(intent.getExtras().getString("nama"));
        ambilDarah.setText(intent.getExtras().getString("goldar"));
        ambilAlamat.setText(intent.getExtras().getString("alamat"));
        ambilNo.setText(intent.getExtras().getString("no"));


        // Panggil method yang sesuai untuk mendapatkan data darah darurat
        fetchDataDarahDarurat();

        btnUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(darahDarurat.this, utama.class);

                startActivity(intentPindah);
            }
        });
        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(darahDarurat.this, listRiwayat.class);

                startActivity(intentPindah);
            }
        });

        btnRspmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(darahDarurat.this, listRspmi.class);

                startActivity(intentPindah);
            }
        });
        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(darahDarurat.this, profil.class);

                startActivity(intentPindah);
            }
        });
    }

    private void fetchDataDarahDarurat() {

        Call<List<DataModalDarahDarurat>> call =  RetroServer.getRetrofitAPI().getDataDarahDaruratList();
        call.enqueue(new Callback<List<DataModalDarahDarurat>>() {
            @Override
            public void onResponse(Call<List<DataModalDarahDarurat>> call, Response<List<DataModalDarahDarurat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Jika respons berhasil dan body tidak null, inisialisasi adapter dan set ke dalam ListView
                    List<DataModalDarahDarurat> data = response.body();
                    Log.e("Error", "Request failed: " + response.body());
                    adapter = new DarahDaruratAdapter(darahDarurat.this, data, ambilNik.getText().toString(), ambilNama.getText().toString(), ambilDarah.getText().toString(), ambilAlamat.getText().toString(), ambilNo.getText().toString());
                    Log.e("Error", "Request failed: " + adapter);
                    lvDarahDarurat.setAdapter(adapter);
                } else {
                    // Tampilkan pesan jika respons tidak berhasil atau body null
                    Toast.makeText(darahDarurat.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModalDarahDarurat>> call, Throwable t) {
                // Tangani kasus ketika permintaan gagal (misalnya, masalah jaringan)
                Toast.makeText(darahDarurat.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "Request failed: " + t.getMessage());
                Log.e("Error", "Request failed: " + Log.getStackTraceString(t));

            }
        });
    }
}