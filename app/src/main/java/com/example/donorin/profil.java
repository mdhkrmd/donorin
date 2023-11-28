package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class profil extends AppCompatActivity {

    TextView ambilNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ambilNama = findViewById(R.id.txtNama);

        Intent intent = getIntent();
//        ambilUsername.setText(intent.getStringExtra("username"));
        ambilNama.setText(intent.getExtras().getString("nama"));
    }
}