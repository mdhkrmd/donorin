package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profil extends AppCompatActivity {

    TextView ambilNama, txtUpdate, txtLogout, ambilNik, ambilDarah, ambilAlamat, ambilLahir, ambilNo, btnUtama2, btnRiwayat, btnRspmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ambilNama = findViewById(R.id.ambilNama);
        ambilNik = findViewById(R.id.ambilNik);
        ambilLahir = findViewById(R.id.ambilLahir);
        ambilDarah = findViewById(R.id.ambilDarah);
        ambilAlamat = findViewById(R.id.ambilAlamat);
        ambilNo = findViewById(R.id.ambilNo);
        btnUtama2 = findViewById(R.id.btnUtama2);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);


        txtUpdate = findViewById(R.id.txtUpdate);
        txtLogout = findViewById(R.id.txtLogout);

        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, updateProfil.class);
                intentPindah.putExtra("nik", ambilNik.getText().toString());
                intentPindah.putExtra("nama", ambilNama.getText().toString());
                intentPindah.putExtra("lahir", ambilLahir.getText().toString());
                intentPindah.putExtra("darah", ambilDarah.getText().toString());
                intentPindah.putExtra("alamat", ambilAlamat.getText().toString());
                intentPindah.putExtra("no", ambilNo.getText().toString());



                startActivity(intentPindah);
            }
        });

        btnUtama2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, utama.class);

                startActivity(intentPindah);
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, listRiwayat.class);

                startActivity(intentPindah);
            }
        });

        btnRspmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, listRspmi.class);

                startActivity(intentPindah);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.clearLoginPreferences(profil.this);
                Intent intentPindah = new Intent(profil.this, login.class);
                startActivity(intentPindah);
                finish();
            }
        });

        Intent intent = getIntent();
        ambilNik.setText(intent.getStringExtra("nik"));
        ambilNama.setText(intent.getExtras().getString("nama"));
        ambilLahir.setText(intent.getExtras().getString("lahir"));
        ambilDarah.setText(intent.getExtras().getString("darah"));
        ambilAlamat.setText(intent.getExtras().getString("alamat"));
        ambilNo.setText(intent.getExtras().getString("no"));
    }


}