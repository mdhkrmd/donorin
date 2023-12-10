package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class reqDarah extends AppCompatActivity {

    EditText inputNama, inputDeskripsi;
    Spinner inputGoldar;
    Button btnReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_darah);

        inputNama = findViewById(R.id.inputNama);
        inputDeskripsi = findViewById(R.id.inputDeskripsi);
        inputGoldar = findViewById(R.id.inputGoldar);
        btnReq = findViewById(R.id.btnReq);

        String[] tipeDarah = {"A+", "A-", "B+", "B-", "O+"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipeDarah);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputGoldar.setAdapter(adapter);

        inputGoldar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item
                String selectedBloodType = tipeDarah[position];
                Toast.makeText(reqDarah.this, "Selected: " + selectedBloodType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahDataDarahDarurat();
            }
        });
    }
    private void tambahDataDarahDarurat() {
        String nama = inputNama.getText().toString();
        String golonganDarah = inputGoldar.getSelectedItem().toString();
        String deskripsi = inputDeskripsi.getText().toString();

        if (nama.isEmpty() || golonganDarah.isEmpty() || deskripsi.isEmpty() || deskripsi.isEmpty()) {
            Toast.makeText(reqDarah.this, "Semua data diperlukan", Toast.LENGTH_SHORT).show();
            return;
        }

        DataModalDarahDarurat newData = new DataModalDarahDarurat(null, nama, golonganDarah, deskripsi, null, null);

        Call<DataModalDarahDarurat> call = RetroServer.getRetrofitAPI().createDarahDarurat(newData);
        call.enqueue(new Callback<DataModalDarahDarurat>() {
            @Override
            public void onResponse(Call<DataModalDarahDarurat> call, Response<DataModalDarahDarurat> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(reqDarah.this, "Data darah darurat berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(reqDarah.this, "Gagal menambahkan data darah darurat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModalDarahDarurat> call, Throwable t) {
                Toast.makeText(reqDarah.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "Request failed: " + t.getMessage());
                Log.e("Error", "Request failed: " + Log.getStackTraceString(t));
            }
        });
    }
    private void clearFields() {
        inputNama.setText("");
        inputGoldar.setSelection(0);
        inputDeskripsi.setText("");
    }
}