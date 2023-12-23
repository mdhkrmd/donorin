package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class utama extends AppCompatActivity {

    TextView ambilUsername, ambilNik, ambilNama, ambilLahir, ambilDarah, ambilNo, ambilPoin, ambilAlamat;
    TextView btnDaftar, btnDarurat, reqDarah, btnUtama,  btnRiwayat, btnRspmi, btnPengaturan;

    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.Adapter adapterTrendList;
    private RecyclerView recyclerViewTrends, rvArtikel;

    private static final String PREFS_NAME = "YourPrefsFile";
    private static final String KEY_NIK = "nik";

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

        rvArtikel = findViewById(R.id.view1);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDarurat = findViewById(R.id.btnDarurat);
        reqDarah = findViewById(R.id.reqDarah);

        btnUtama = findViewById(R.id.btnUtama);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRspmi = findViewById(R.id.btnRspmi);
        btnPengaturan = findViewById(R.id.btnPengaturan);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        String nik = getStoredNik();

        // Jika nik belum tersimpan (nilai default), ambil dari intent
        if (nik.equals("default_value")) {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("nik")) {
                nik = intent.getStringExtra("nik");

                // Simpan nik ke SharedPreferences
                storeNik(utama.this, nik);
            }
        }

        getDataLogin(nik);

//        ambilUsername.setText(intent.getExtras().getString("username"));
//        ambilNama.setText(intent.getExtras().getString("nik"));
//        ambilNik.setText(intent.getExtras().getString("nik"));
//        ambilLahir.setText(intent.getExtras().getString("lahir"));
//        ambilDarah.setText(intent.getExtras().getString("goldar"));
//        ambilNo.setText(intent.getExtras().getString("no"));
//        ambilPoin.setText(String.valueOf(intent.getIntExtra("poin", 0)));
//        ambilPoin.setText(intent.getExtras().getString("goldar"));
//        ambilAlamat.setText(intent.getExtras().getString("alamat"));


        getArtikel();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Panggil metode untuk memuat ulang data, misalnya fetchDataDarahDarurat()
                getArtikel();
//                getDataLogin(nik);
                swipeRefreshLayout.setRefreshing(false); // Hentikan animasi refresh
            }
        });


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

                intentPindah.putExtra("nik", ambilNik.getText().toString());
                intentPindah.putExtra("nama", ambilNama.getText().toString());
                intentPindah.putExtra("goldar", ambilDarah.getText().toString());
                intentPindah.putExtra("alamat", ambilAlamat.getText().toString());
                intentPindah.putExtra("no", ambilNo.getText().toString());

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
                intentPindah.putExtra("nik", ambilNik.getText().toString());
                intentPindah.putExtra("nama", ambilNama.getText().toString());
                intentPindah.putExtra("lahir", ambilLahir.getText().toString());
                intentPindah.putExtra("darah", ambilDarah.getText().toString());
                intentPindah.putExtra("alamat", ambilAlamat.getText().toString());
                intentPindah.putExtra("no", ambilNo.getText().toString());

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
    private void getArtikel() {
        Call<List<ArtikelData>> apiCall =  RetroServer.getRetrofitAPI().getArtikel();
        apiCall.enqueue(new Callback<List<ArtikelData>>() {
            @Override
            public void onResponse(Call<List<ArtikelData>> call, Response<List<ArtikelData>> response) {
                List<ArtikelData> artikelDataList = response.body();
//                Toast.makeText(utama.this, "Artikel terambil", Toast.LENGTH_SHORT).show();
                setAdapter(artikelDataList);
            }

            @Override
            public void onFailure(Call<List<ArtikelData>> call, Throwable t) {
                Toast.makeText(utama.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<ArtikelData> artikelDataList) {
        rvArtikel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArtikelAdapter artikelAdapter = new ArtikelAdapter(this, artikelDataList);
        rvArtikel.setAdapter(artikelAdapter);
    }

    public static void storeNik(Context context, String nik) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NIK, nik);
        editor.apply();
    }

    private String getStoredNik() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getString(KEY_NIK, "default_value");
    }

    @Override
    public void onBackPressed() {
        // Tambahkan logika untuk menutup aplikasi atau melakukan aksi khusus
        // Misalnya, keluar dari aplikasi saat tombol "back" ditekan setelah login
        super.onBackPressed();
        finishAffinity(); // Menutup semua aktivitas di dalam tumpukan
    }

    private void getDataLogin(final String nik) {
        Call<List<DataModalLogin>> apiCall = RetroServer.getRetrofitAPI().getLoginData(nik);
        apiCall.enqueue(new Callback<List<DataModalLogin>>() {
            @Override
            public void onResponse(Call<List<DataModalLogin>> call, Response<List<DataModalLogin>> response) {
                if (response.isSuccessful()) {
                    List<DataModalLogin> loginData = response.body();

                    // Check if the login data is not null
                    if (loginData != null && !loginData.isEmpty()) {
                        // Assuming getNama() is a method in DataModalLogin to get the name

                        String nik = loginData.get(0).getNik();
                        String nama = loginData.get(0).getNama();
                        String goldar = loginData.get(0).getDarah();
                        String alamat = loginData.get(0).getAlamat();
                        String no = loginData.get(0).getNohp();
                        String lahir = loginData.get(0).getLahir();

                        // Assuming ambilNama is a TextView

                        ambilNik.setText(nik);
                        ambilNama.setText(nama);
                        ambilPoin.setText(goldar);
                        ambilDarah.setText(goldar);
                        ambilAlamat.setText(alamat);
                        ambilNo.setText(no);
                        ambilLahir.setText(lahir);

                        Toast.makeText(utama.this, "Data Login terambil", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(utama.this, "Login data is null or empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(utama.this, "Failed to get login data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModalLogin>> call, Throwable t) {
                Toast.makeText(utama.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}