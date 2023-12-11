package com.example.donorin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DarahDaruratAdapter extends BaseAdapter {
    private Context context;
    private List<DataModalDarahDarurat> dataList;
    private String nik, nama, goldar, alamat, no;

    public DarahDaruratAdapter(Context context, List<DataModalDarahDarurat> dataList, String nik, String nama, String goldar, String alamat, String no) {
        this.context = context;
        this.dataList = dataList;
        this.nik = nik;
        this.nama = nama;
        this.goldar = goldar;
        this.alamat = alamat;
        this.no = no;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rv_darahdarurat_item, null);
        }

        // Mendapatkan data untuk posisi tertentu
        DataModalDarahDarurat data = dataList.get(position);

        // Inisialisasi TextView dari layout item
        TextView idTextView = convertView.findViewById(R.id.text_id);
        TextView namaTextView = convertView.findViewById(R.id.text_nama);
        TextView golDarahTextView = convertView.findViewById(R.id.text_gol_darah);
        TextView statusTextView = convertView.findViewById(R.id.text_status);
        TextView tanggalTextView = convertView.findViewById(R.id.txtTanggal);

        Button btnDaftarDarurat = convertView.findViewById(R.id.btnDaftarDarurat);

        // Mengatur nilai TextView sesuai data yang diterima
        idTextView.setText("ID: " + data.getIdDarahDarurat());
        namaTextView.setText("Nama: " + data.getNamaDarahDarurat());
        golDarahTextView.setText("Golongan Darah: " + data.getGol_darahDarurat());
        statusTextView.setText("Status: " + data.getStatusDarahDarurat());
        tanggalTextView.setText("Tanggal: " + data.getTanggalDarahDarurat());

        btnDaftarDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dapatkan data yang ingin Anda kirim
                String penerima = data.getNamaDarahDarurat();
                String goldarPenerima = data.getGol_darahDarurat();
                String idPenerima = data.getIdDarahDarurat();

                // Buat Intent untuk berpindah ke daftarDarahDarurat.java
                Intent intent = new Intent(view.getContext(), daftarDarahDarurat.class);

                // Masukkan data ke Intent
                intent.putExtra("idPenerima", idPenerima);
                intent.putExtra("penerima", penerima);
                intent.putExtra("goldarPenerima", goldarPenerima);
                intent.putExtra("nik", nik);
                intent.putExtra("nama", nama);
                intent.putExtra("goldar", goldar);
                intent.putExtra("alamat", alamat);
                intent.putExtra("no", no);
                // Start aktivitas baru dengan mengirimkan data
                view.getContext().startActivity(intent);
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan context dari view
                Context context = v.getContext();

                // Buka halaman detail dan kirim data yang diperlukan
                Intent intent = new Intent(context, detailDarahDarurat.class);

//                intent.putExtra("idDarahDarurat", data.getIdDarahDarurat());
                intent.putExtra("namaDarahDarurat", data.getNamaDarahDarurat());
                intent.putExtra("goldarDarahDarurat", data.getGol_darahDarurat());
                intent.putExtra("deskripsiDarahDarurat", data.getDeskripsiDarahDarurat());
//                intent.putExtra("statusDarahDarurat", data.getStatusDarahDarurat());
//                intent.putExtra("tanggalDarahDarurat", data.getTanggalDarahDarurat());

                // Mulai aktivitas detail
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}