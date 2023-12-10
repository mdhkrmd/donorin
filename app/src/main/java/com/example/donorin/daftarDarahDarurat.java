package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class daftarDarahDarurat extends AppCompatActivity {

    EditText editPenerima, editDarahPenerima;
    EditText editNik, editNama, editDarah, editAlamat, editNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_darah_darurat);

        editPenerima = findViewById(R.id.editPenerima);
        editDarahPenerima = findViewById(R.id.editDarahPenerima);

        editNik = findViewById(R.id.editNik);
        editNama = findViewById(R.id.editNama);
        editDarah = findViewById(R.id.editDarah);
        editAlamat = findViewById(R.id.editAlamat);
        editNo = findViewById(R.id.editNo);


        Intent intent = getIntent();
        String penerima = intent.getStringExtra("penerima");
        String goldarPenerima = intent.getStringExtra("goldarPenerima");
        String nik = intent.getStringExtra("nik");
        String nama = intent.getStringExtra("nama");
        String goldar = intent.getStringExtra("goldar");
        String alamat = intent.getStringExtra("alamat");
        String no = intent.getStringExtra("no");

        editPenerima.setText(penerima);
        editDarahPenerima.setText(goldarPenerima);
        editNik.setText(nik);
        editNama.setText(nama);
        editDarah.setText(goldar);
        editAlamat.setText(alamat);
        editNo.setText(no);
    }
}