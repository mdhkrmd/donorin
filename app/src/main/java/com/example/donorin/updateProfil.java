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

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateProfil extends AppCompatActivity {

    EditText ambilNik, ambilNama, ambilLahir, ambilAlamat, ambilNo;
    Spinner ambilDarah;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);

        ambilNik = findViewById(R.id.editNik);
        ambilNama = findViewById(R.id.editNama);
        ambilLahir = findViewById(R.id.editLahir);
        ambilDarah = findViewById(R.id.editDarah);
        ambilAlamat = findViewById(R.id.editAlamat);
        ambilNo = findViewById(R.id.editNo);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        ambilNik.setText(intent.getStringExtra("nik"));
        ambilNama.setText(intent.getExtras().getString("nama"));
        ambilLahir.setText(intent.getExtras().getString("lahir"));
        ambilAlamat.setText(intent.getExtras().getString("alamat"));
        ambilNo.setText(intent.getExtras().getString("no"));

        String[] tipeDarah = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipeDarah);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ambilDarah.setAdapter(adapter);

        int position = Arrays.asList(tipeDarah).indexOf(intent.getExtras().getString("darah"));
        ambilDarah.setSelection(position);
        ambilDarah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item
                String selectedBloodType = tipeDarah[position];
                Toast.makeText(updateProfil.this, "Selected: " + selectedBloodType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (ambilNik.getText().toString().trim().length()==0
                        || ambilNama.getText().toString().trim().length()==0
                        || ambilLahir.getText().toString().trim().length()==0
                        || ambilDarah.getSelectedItem().toString().trim().length()==0
                        || ambilAlamat.getText().toString().trim().length()==0
                        || ambilNo.getText().toString().trim().length()==0) {
                    Toast.makeText(updateProfil.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postDataUpdate(ambilNik.getText().toString(),
                            ambilNama.getText().toString(),
                            ambilLahir.getText().toString(),
                            ambilDarah.getSelectedItem().toString(),
                            ambilAlamat.getText().toString(),
                            ambilNo.getText().toString());
                }
            }
        });
    }

    private void postDataUpdate(String nikUpdate, String namaUpdate, String lahirUpdate, String darahUpdate, String alamatUpdate, String nohpUpdate) {

        // passing data from our text fields to our modal class.
        DataModalUpdateProfil modal = new DataModalUpdateProfil(nikUpdate, namaUpdate, lahirUpdate, darahUpdate, alamatUpdate, nohpUpdate);

        // calling a method to create a post and passing our modal class.
        Call<DataModalUpdateProfil> call = RetroServer.getRetrofitAPI().createPostUpdate(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalUpdateProfil>() {
            @Override
            public void onResponse(Call<DataModalUpdateProfil> call, Response<DataModalUpdateProfil> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DataModalUpdateProfil responseFromAPI = response.body();

                    // Assuming there is a "status" field in the response indicating success
                    if (responseFromAPI.getStatus().equals("success")) {
                        Toast.makeText(updateProfil.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(updateProfil.this, utama.class);
                        startActivity(intent);
                        finish(); //
                    } else {
                        Toast.makeText(updateProfil.this, "Gagal Mengubah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response (HTTP error, network issues, etc.)
                    Toast.makeText(updateProfil.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModalUpdateProfil> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}