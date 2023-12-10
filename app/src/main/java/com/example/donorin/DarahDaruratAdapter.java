package com.example.donorin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DarahDaruratAdapter extends BaseAdapter {
    private Context context;
    private List<DataModalDarahDarurat> dataList;

    public DarahDaruratAdapter(Context context, List<DataModalDarahDarurat> dataList) {
        this.context = context;
        this.dataList = dataList;
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

        // Mengatur nilai TextView sesuai data yang diterima
        idTextView.setText("ID: " + data.getIdDarahDarurat());
        namaTextView.setText("Nama: " + data.getNamaDarahDarurat());
        golDarahTextView.setText("Golongan Darah: " + data.getGol_darahDarurat());
        statusTextView.setText("Status: " + data.getStatusDarahDarurat());
        tanggalTextView.setText("Tanggal: " + data.getTanggalDarahDarurat());

        return convertView;
    }
}
