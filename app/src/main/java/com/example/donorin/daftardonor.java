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

    EditText EditNik, EditNama, EditDarah, EditAlamat, EditNo;
    Spinner EditJadwal;
    Button btnDaftar;
    Spinner spinnerLokasi;
    TextView responseTV;
    private List<String> dataList = new ArrayList<>();
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
//        responseTV = findViewById(R.id.idTVResponse);

        Intent intent = getIntent();
        EditNik.setText(intent.getStringExtra("nik"));
        EditNama.setText(intent.getExtras().getString("nama"));
        EditDarah.setText(intent.getExtras().getString("goldar"));
        EditAlamat.setText(intent.getExtras().getString("alamat"));
        EditNo.setText(intent.getExtras().getString("no"));

        getDataFromAPI();

        String[] jadwal = {"08.00 - 11.30", "13.00 - 16.00", "19.00 - 21.00"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jadwal);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        EditJadwal.setAdapter(adapter);

        EditJadwal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item
                String selectedBloodType = jadwal[position];
                Toast.makeText(daftardonor.this, "Selected: " + selectedBloodType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (EditJadwal.getSelectedItem().toString().trim().length()==0 ||
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
                            EditJadwal.getSelectedItem().toString());
                }
            }
        });
    }
    private void postData(String nik, String nama, String darah, String alamat, String nohp, String lokasi, String jadwal) {

        // passing data from our text fields to our modal class.
        DataModalDaftar modal = new DataModalDaftar(nik, nama, darah, alamat, nohp, lokasi, jadwal, null);

        // calling a method to create a post and passing our modal class.
        Call<DataModalDaftar> call = RetroServer.getRetrofitAPI().createPostDaftar(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalDaftar>() {
            @Override
            public void onResponse(Call<DataModalDaftar> call, Response<DataModalDaftar> response) {
                // this method is called when we get response from our api.
                Toast.makeText(daftardonor.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();

                spinnerLokasi.setSelection(0);
                EditJadwal.setSelection(0);

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

    private void getDataFromAPI() {
        // Panggil endpoint untuk mendapatkan data dari API
        Call<List<RspmiData >> call = RetroServer.getRetrofitAPI().getData();
        call.enqueue(new Callback<List<RspmiData >>() {
            @Override
            public void onResponse(Call<List<RspmiData >> call, Response<List<RspmiData >> response) {
                if (response.isSuccessful()) {
                    List<RspmiData > rspmiDataList = response.body();

                    if (rspmiDataList != null && !rspmiDataList.isEmpty()) {
                        List<String> namaList = new ArrayList<>();
                        for (RspmiData  rspmiData : rspmiDataList) {
                            // Ambil nilai dari properti "nama" dan tambahkan ke daftar
                            namaList.add(rspmiData.getNamaRspmi());
                        }

                        // Set data ke Spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(daftardonor.this,
                                android.R.layout.simple_spinner_item, namaList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerLokasi.setAdapter(adapter);

                        // Menambahkan listener untuk menangani pemilihan item
                        spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Tindakan yang akan diambil saat item dipilih
                                String selectedItem = namaList.get(position);
                                Toast.makeText(daftardonor.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // Tindakan yang akan diambil jika tidak ada yang dipilih
                            }
                        });
                    } else {
                        Toast.makeText(daftardonor.this, "Data list is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(daftardonor.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RspmiData >> call, Throwable t) {
                Toast.makeText(daftardonor.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}