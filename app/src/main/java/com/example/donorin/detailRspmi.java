package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailRspmi extends AppCompatActivity {

    private ImageView ivProduct;
    private TextView tvNama, tvAlamat, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rspmi);

        // Inisialisasi elemen UI
        ivProduct = findViewById(R.id.ivProduct);
        tvNama = findViewById(R.id.tvNama);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        Bundle bundle = getIntent().getExtras();

        String foto = bundle.getString("poster");
        String nama = bundle.getString("nama");
        String alamat = bundle.getString("alamat");
        String deskripsi = bundle.getString("deskripsi");

        Glide.with(this).load(foto).into(ivProduct);
        tvNama.setText(nama);
        tvAlamat.setText(alamat);
        tvDeskripsi.setText(deskripsi);
    }
}
