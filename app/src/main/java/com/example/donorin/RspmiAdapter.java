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


public class RspmiAdapter extends RecyclerView.Adapter<RspmiAdapter.RspmiViewHolder> {

    private Context context;
    private List<RspmiData> rspmiList;

    public RspmiAdapter(Context context, List<RspmiData> rspmiList) {
        this.context = context;
        this.rspmiList = rspmiList;
    }

    @NonNull
    @Override
    public RspmiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_rspmi_item, parent, false);
        return new RspmiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RspmiViewHolder holder, int position) {
        RspmiData rspmiData = rspmiList.get(position);
        Glide.with(context)
                .load(rspmiList.get(position).getFoto())
                .into(holder.ivImage);

        holder.tvTitle.setText(rspmiData.getNamaRspmi());
        holder.tvAlamat.setText(rspmiData.getAlamatRspmi());
        holder.tvAlamat.setText(rspmiData.getDeskripsiRspmi());

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailRspmi.class);

                Bundle bundle = new Bundle();
                bundle.putString("nama" , rspmiData.getNamaRspmi());
                bundle.putString("alamat" , rspmiData.getAlamatRspmi());
                bundle.putString("poster" , rspmiData.getFoto());
                bundle.putString("deskripsi" , rspmiData.getDeskripsiRspmi());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rspmiList.size();
    }

    static class RspmiViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvAlamat, tvDeskripsi;

        RspmiViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
        }
    }
}
