package com.example.donorin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.donorin.R;

import java.util.ArrayList;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.Viewholder> {
    ArrayList<ArtikelData> items;
    Context context;

    public ArtikelAdapter(ArrayList<ArtikelData> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ArtikelAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_trend_list, parent, false);
        context = parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelAdapter.Viewholder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.subtitle.setText(items.get(position).getSubtitle());

        int drawableResourceId=holder.itemView.getResources()
                .getIdentifier(items.get(position).getPicPath(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            subtitle = itemView.findViewById(R.id.subtitleTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
