package com.example.donorin;

import android.content.Context;
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
    }

    @Override
    public int getItemCount() {
        return rspmiList.size();
    }

    static class RspmiViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;

        RspmiViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
