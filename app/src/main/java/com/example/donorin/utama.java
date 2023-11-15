package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class utama extends AppCompatActivity {

    TextView ambilUsername, ambilNik, ambilNama, ambilLahir, ambilDarah, ambilNo, ambilPoin, ambilAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        ambilUsername = findViewById(R.id.ambilUsername);
        ambilNama = findViewById(R.id.ambilNama);
        ambilNik = findViewById(R.id.ambilNik);
        ambilLahir = findViewById(R.id.ambilLahir);
        ambilDarah = findViewById(R.id.ambilDarah);
        ambilNo = findViewById(R.id.ambilNo);
        ambilPoin = findViewById(R.id.ambilPoin);
        ambilAlamat = findViewById(R.id.ambilAlamat);
        Intent intent = getIntent();

        ambilUsername.setText(intent.getStringExtra("username"));
        ambilNama.setText(intent.getExtras().getString("nama"));
        ambilNik.setText(intent.getExtras().getString("nik"));
        ambilLahir.setText(intent.getExtras().getString("lahir"));
        ambilDarah.setText(intent.getExtras().getString("goldar"));
        ambilNo.setText(intent.getExtras().getString("no"));
        ambilPoin.setText(String.valueOf(intent.getIntExtra("poin", 0)));
        ambilAlamat.setText(intent.getExtras().getString("alamat"));
    }
}