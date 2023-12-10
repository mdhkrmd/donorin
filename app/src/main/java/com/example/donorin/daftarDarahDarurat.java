package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class daftarDarahDarurat extends AppCompatActivity {

    EditText editPenerima, editDarahPenerima;
    EditText editNik, editNama, editDarah, editAlamat, editNo;
    Button btnDaftar;

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
        btnDaftar = findViewById(R.id.btnDaftar);

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

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (!editDarah.getText().toString().equals(editDarahPenerima.getText().toString())) {
                    Toast.makeText(daftarDarahDarurat.this, "Golongan Darah Berbeda", Toast.LENGTH_SHORT).show();
                }
                else{
                    postData(editNik.getText().toString(),
                            editNama.getText().toString(),
                            editDarah.getText().toString(),
                            editAlamat.getText().toString(),
                            editNo.getText().toString(),
                            editPenerima.getText().toString());
                }
            }
        });
    }
    private void postData(String nik, String nama, String darah, String alamat, String nohp, String lokasi) {

        // passing data from our text fields to our modal class.
        DataModalDaftar modal = new DataModalDaftar(nik, nama, darah, alamat, nohp, lokasi, null);

        // calling a method to create a post and passing our modal class.
        Call<DataModalDaftar> call = RetroServer.getRetrofitAPI().createPostDaftarDonorDarurat(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalDaftar>() {
            @Override
            public void onResponse(Call<DataModalDaftar> call, Response<DataModalDaftar> response) {
                // this method is called when we get response from our api.
                Toast.makeText(daftarDarahDarurat.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();

                // we are getting response from our body
                // and passing it to our modal class.
                DataModalDaftar responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
//                String responseString = "Response Code : " + response.code();
//                responseTV.setText(responseString);

            }
            @Override
            public void onFailure(Call<DataModalDaftar> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}