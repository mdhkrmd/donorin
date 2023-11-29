package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profil extends AppCompatActivity {

    TextView ambilNama, txtUpdate, txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ambilNama = findViewById(R.id.txtNama);
        txtUpdate = findViewById(R.id.txtUpdate);
        txtLogout = findViewById(R.id.txtLogout);

        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPindah = new Intent(profil.this, updateProfil.class);

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
//        ambilUsername.setText(intent.getStringExtra("username"));
        ambilNama.setText(intent.getExtras().getString("nama"));
    }


}