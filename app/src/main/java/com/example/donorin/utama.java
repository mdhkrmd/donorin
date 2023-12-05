package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class utama extends AppCompatActivity {

    TextView ambilUsername, ambilNik, ambilNama, ambilLahir, ambilDarah, ambilNo, ambilPoin, ambilAlamat;
    TextView btnDaftar, btnDarurat, reqDarah, btnUtama,  btnRiwayat, btnRspmi, btnPengaturan;
    private RecyclerView.Adapter adapterTrendList;
    private RecyclerView recyclerViewTrends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

//        ambilUsername = findViewById(R.id.ambilUsername);
        ambilNama = findViewById(R.id.ambilNama);
        ambilNik = findViewById(R.id.ambilNik);
        ambilLahir = findViewById(R.id.ambilLahir);
        ambilDarah = findViewById(R.id.ambilDarah);
        ambilNo = findViewById(R.id.ambilNo);
        ambilPoin = findViewById(R.id.ambilPoin);
        ambilAlamat = findViewById(R.id.ambilAlamat);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDarurat = findViewById(R.id.btnDarurat);
        reqDarah = findViewById(R.id.reqDarah);

        btnUtama = findViewById(R.id.btnUtama);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);
        btnPengaturan = findViewById(R.id.btnPengaturan);

        Intent intent = getIntent();
//
//        ambilUsername.setText(intent.getStringExtra("username"));
        ambilNama.setText(intent.getExtras().getString("nama"));
        ambilNik.setText(intent.getExtras().getString("nik"));
        ambilLahir.setText(intent.getExtras().getString("lahir"));
        ambilDarah.setText(intent.getExtras().getString("goldar"));
        ambilNo.setText(intent.getExtras().getString("no"));
        ambilPoin.setText(String.valueOf(intent.getIntExtra("poin", 0)));
        ambilAlamat.setText(intent.getExtras().getString("alamat"));

        initRecyclerView();


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, daftardonor.class);

                intentPindah.putExtra("nik", ambilNik.getText().toString());
                intentPindah.putExtra("nama", ambilNama.getText().toString());
                intentPindah.putExtra("goldar", ambilDarah.getText().toString());
                intentPindah.putExtra("alamat", ambilAlamat.getText().toString());
                intentPindah.putExtra("no", ambilNo.getText().toString());

                startActivity(intentPindah);
            }
        });

        btnDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, darahDarurat.class);

                startActivity(intentPindah);
            }
        });

        reqDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, reqDarah.class);

                startActivity(intentPindah);
            }
        });
        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, profil.class);
                intentPindah.putExtra("nama", ambilNama.getText().toString());

                startActivity(intentPindah);
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, listRiwayat.class);
                intentPindah.putExtra("nik", ambilNik.getText().toString());

                startActivity(intentPindah);
            }
        });

        btnRspmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(utama.this, listRspmi.class);

                startActivity(intentPindah);
            }
        });
    }
    private void initRecyclerView() {
        ArrayList<ArtikelData> items = new ArrayList<>();
        items.add(new ArtikelData("Lorem Ipsum Judul", "Konteks Konteks Konteks", "trends"));
        items.add(new ArtikelData("Lorem Ipsum Judul", "Konteks Konteks Konteks", "trends2"));
        items.add(new ArtikelData("Lorem Ipsum Judul", "Konteks Konteks Konteks", "trends3"));

        recyclerViewTrends = findViewById(R.id.view1);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterTrendList = new ArtikelAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendList);
    }
}