package com.example.donorin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    }
}