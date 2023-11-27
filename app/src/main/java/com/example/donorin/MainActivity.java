package com.example.donorin;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword, inputNik, inputNama, inputLahir, inputDarah, inputAlamat, inputNo;
    private Button postDataBtn;
    private TextView responseTV, tvLogin;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.editUsername);
        inputPassword = findViewById(R.id.editPassword);
        inputNik = findViewById(R.id.editNik);
        inputNama = findViewById(R.id.editNama);
        inputLahir = findViewById(R.id.editLahir);
        inputDarah = findViewById(R.id.editDarah);
        inputAlamat = findViewById(R.id.editAlamat);
        inputNo = findViewById(R.id.editNo);

        tvLogin = findViewById(R.id.tvLogin);

        postDataBtn = findViewById(R.id.idBtnPost);
//        responseTV = findViewById(R.id.idTVResponse);
//        loadingPB = findViewById(R.id.idLoadingPB);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang akan diambil saat TextView (txtForgot) diklik
                // Misalnya, pindah ke halaman baru menggunakan Intent

                Intent intent = new Intent(MainActivity.this, login.class);
                // Gantilah NamaActivitySaatIni dan NamaActivityTujuan dengan nama aktivitas sebenarnya

                // Jika Anda ingin membawa data tambahan ke aktivitas tujuan, Anda dapat menggunakan putExtra
                // intent.putExtra("key", "value");

                startActivity(intent);
            }
        });

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (inputUsername.getText().toString().trim().length()==0
                        || inputPassword.getText().toString().trim().length()==0
                        || inputNik.getText().toString().trim().length()==0
                        || inputNama.getText().toString().trim().length()==0
                        || inputLahir.getText().toString().trim().length()==0
                        || inputDarah.getText().toString().trim().length()==0
                        || inputAlamat.getText().toString().trim().length()==0
                        || inputNo.getText().toString().trim().length()==0) {
                    Toast.makeText(MainActivity.this, "Mohon lengkapi yang masih kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    postData(inputUsername.getText().toString(),
                    inputPassword.getText().toString(),
                    inputNik.getText().toString(),
                    inputNama.getText().toString(),
                    inputLahir.getText().toString(),
                    inputDarah.getText().toString(),
                    inputAlamat.getText().toString(),
                    inputNo.getText().toString());
                }
            }
        });
    }
    private void postData(String username, String password, String nik, String nama, String lahir, String darah, String alamat, String nohp) {

        // below line is for displaying our progress bar.
//        loadingPB.setVisibility(View.VISIBLE);

        // passing data from our text fields to our modal class.
        DataModalRegister modal = new DataModalRegister(username, password, nik, nama, lahir, darah, alamat, nohp);

        // calling a method to create a post and passing our modal class.
        Call<DataModalRegister> call = RetroServer.getRetrofitAPI().createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModalRegister>() {
            @Override
            public void onResponse(Call<DataModalRegister> call, Response<DataModalRegister> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
//                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                inputUsername.setText("");
                inputPassword.setText("");
                inputNik.setText("");
                inputNama.setText("");
                inputLahir.setText("");
                inputDarah.setText("");
                inputAlamat.setText("");
                inputNo.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModalRegister responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code();

                // below line we are setting our
                // string to our text view.
//                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModalRegister> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}