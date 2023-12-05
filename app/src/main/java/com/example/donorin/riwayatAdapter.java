package com.example.donorin;
import android.content.Context;
import android.content.Intent;
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

        holder.txtRspmi.setText(riwayatData.getNamaRiwayat());
        holder.txtStatus.setText(riwayatData.getStatusRiwayat());

    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    static class riwayatViewHolder extends RecyclerView.ViewHolder {
        TextView txtRspmi, txtStatus;

        riwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRspmi = itemView.findViewById(R.id.txtRspmi);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
