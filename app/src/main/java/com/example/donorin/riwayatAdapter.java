package com.example.donorin;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
public class riwayatAdapter extends RecyclerView.Adapter<riwayatAdapter.riwayatViewHolder> {
    private Context context;
    private List<riwayatData> riwayatList;

    public riwayatAdapter(Context context, List<riwayatData> riwayatList) {
        this.context = context;
        this.riwayatList = riwayatList;
    }

    @NonNull
    @Override
    public riwayatAdapter.riwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_riwayat_item, parent, false);
        return new riwayatAdapter.riwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull riwayatAdapter.riwayatViewHolder holder, int position) {
        riwayatData riwayatData = riwayatList.get(position);

        String status = riwayatData.getStatusRiwayat();

        if ("Proses".equals(status)) {
            // Jika status adalah "proses", set warna kuning
            holder.tvTitle.setBackgroundColor(Color.parseColor("#E3651D"));
        } else if ("Antrian".equals(status)) {
            // Jika status adalah "gagal", set warna merah
            holder.tvTitle.setBackgroundColor(Color.parseColor("#E3651D"));
        }
        else if ("Gagal".equals(status)) {
            // Jika status adalah "gagal", set warna merah
            holder.tvTitle.setBackgroundColor(Color.parseColor("#750E21"));
        }
        else if ("Selesai".equals(status)) {
            // Jika status adalah "gagal", set warna merah
            holder.tvTitle.setBackgroundColor(Color.parseColor("#BED754"));
        }else {
            // Untuk status lain, biarkan warna default atau atur sesuai kebutuhan
            holder.tvTitle.setTextColor(Color.BLACK);
        }

        holder.txtRspmi.setText(riwayatData.getLokasiRiwayat());
        holder.txtStatus.setText(riwayatData.getJadwalRiwayat());
        holder.txtTanggal.setText(riwayatData.getTanggal_daftar());
//        holder.txtNama.setText(riwayatData.getJadwalRiwayat());
        holder.txtId.setText(riwayatData.getIdRiwayat());
        holder.tvTitle.setText(riwayatData.getStatusRiwayat());
    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    static class riwayatViewHolder extends RecyclerView.ViewHolder {
        TextView txtRspmi, txtStatus, tvTitle, txtTanggal, txtNama, txtId;

        riwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRspmi = itemView.findViewById(R.id.txtRspmi);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
//            txtNama = itemView.findViewById(R.id.txtNama);
            txtId = itemView.findViewById(R.id.txtId);
        }
    }
}
