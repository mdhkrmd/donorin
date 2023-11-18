package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class daftardonor extends AppCompatActivity {

    EditText EditNik, EditNama, EditDarah, EditAlamat, EditNo, EditJadwal;
    Button btnDaftar;
    Spinner spinnerLokasi;
    TextView responseTV;
    private List<String> dataList;

    String[] courses = { "C", "Data structures",
            "Interview prep", "Algorithms",
            "Backend with java"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftardonor);

        EditNik = findViewById(R.id.editNik);
        EditNama = findViewById(R.id.editNama);
        EditDarah = findViewById(R.id.editDarah);
        EditAlamat = findViewById(R.id.editAlamat);
        EditNo = findViewById(R.id.editNo);
        EditJadwal = findViewById(R.id.editJadwal);
        spinnerLokasi = findViewById(R.id.spinnerLokasi);
        btnDaftar = findViewById(R.id.btnDaftar);
        responseTV = findViewById(R.id.idTVResponse);

        // Contoh data untuk Spinner
        dataList = new ArrayList<>();
        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 3");

        // Buat adapter untuk Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dataList);

        // Set tata letak dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Terapkan adapter ke Spinner
        spinnerLokasi.setAdapter(adapter);

        Intent intent = getIntent();
        EditNik.setText(intent.getStringExtra("nik"));
        EditNama.setText(intent.getExtras().getString("nama"));
        EditDarah.setText(intent.getExtras().getString("goldar"));
        EditAlamat.setText(intent.getExtras().getString("alamat"));
        EditNo.setText(intent.getExtras().getString("no"));

        spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Tindakan yang akan diambil saat item dipilih
                String selectedItem = dataList.get(position);
                Toast.makeText(daftardonor.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Tindakan yang akan diambil jika tidak ada yang dipilih
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (EditJadwal.getText().toString().trim().length()==0 ||
                spinnerLokasi.getSelectedItem().toString().trim().length()==0) {
                    Toast.makeText(daftardonor.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postData(EditNik.getText().toString(),
                            EditNama.getText().toString(),
                            EditDarah.getText().toString(),
                            EditAlamat.getText().toString(),
                            EditNo.getText().toString(),
                            spinnerLokasi.getSelectedItem().toString(),
                            EditJadwal.getText().toString());
                }
            }
        });
    }
    private void postData(String nik, String nama, String darah, String alamat, String nohp, String lokasi, String jadwal) {

        // passing data from our text fields to our modal class.
        DataModalDaftar modal = new DataModalDaftar(nik, nama, darah, alamat, nohp, lokasi, jadwal);

        // calling a method to create a post and passing our modal class.
        Call<DataModalDaftar> call = RetroServer.getRetrofitAPI().createPostDaftar(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalDaftar>() {
            @Override
            public void onResponse(Call<DataModalDaftar> call, Response<DataModalDaftar> response) {
                // this method is called when we get response from our api.
                Toast.makeText(daftardonor.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();

                // on below line we are setting empty text
                // to our both edit text.
                EditNik.setText("");
                EditNama.setText("");
                EditDarah.setText("");
                EditAlamat.setText("");
                EditNo.setText("");
                spinnerLokasi.setSelection(0);
                EditJadwal.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModalDaftar responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code();
                responseTV.setText(responseString);

            }
            @Override
            public void onFailure(Call<DataModalDaftar> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}