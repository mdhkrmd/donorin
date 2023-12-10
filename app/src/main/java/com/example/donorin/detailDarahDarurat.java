package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailDarahDarurat extends AppCompatActivity {

    TextView tvPemohon, tvGoldar, tvDeskripsi;
    TextView ambilNik, ambilNama, ambilDarah, ambilAlamat, ambilNo;
    Button btnDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_darah_darurat);

        tvPemohon = findViewById(R.id.tvPemohon);
        tvGoldar = findViewById(R.id.tvGoldar);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
//        btnDonor = findViewById(R.id.btnDonor);

        Intent intent = getIntent();

        String nama = intent.getStringExtra("namaDarahDarurat");
        String goldar = intent.getStringExtra("goldarDarahDarurat");
        String deskripsi = intent.getStringExtra("deskripsiDarahDarurat");

        tvPemohon.setText(nama);
        tvGoldar.setText(goldar);
        tvDeskripsi.setText(deskripsi);


//        btnDonor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(detailDarahDarurat.this, daftardonor.class);
//
//                startActivity(intent);
//            }
//        });

    }
}