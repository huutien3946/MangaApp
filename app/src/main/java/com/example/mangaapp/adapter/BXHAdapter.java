package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.function.GetTruyen;
import com.example.mangaapp.model.Truyen;

import java.util.List;
import java.util.Random;

public class BXHAdapter extends RecyclerView.Adapter<BXHAdapter.TruyenTranhViewHolder> {
    private final Context context;
    private final List<Truyen> listTruyenTranh;

    @SuppressLint("NotifyDataSetChanged")
    public BXHAdapter(Context context, List<Truyen> listTruyenTranh) {
        this.context = context;
        this.listTruyenTranh = listTruyenTranh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TruyenTranhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bxh, parent, false);
        return new TruyenTranhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenTranhViewHolder holder, int position) {
        Truyen truyen = listTruyenTranh.get(position);
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        if (truyen != null) {
            holder.tenTruyen.setText(truyen.getTenTruyen());
            holder.tenTruyen.setTextColor(currentColor);
            Glide.with(this.context).load(truyen.getAnhBia()).into(holder.img);
            holder.crvBXH.setOnClickListener(v -> {
                //Click vào chi tiết truyện
                Intent intent = new Intent(context, GetTruyen.class);
                intent.putExtra("clickTruyen", truyen);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listTruyenTranh != null) {
            if (listTruyenTranh.size() >= 10) {
                return 10;
            } else {
                return listTruyenTranh.size();
            }
        }
        return 0;
    }

    public static class TruyenTranhViewHolder extends RecyclerView.ViewHolder {
        private final CardView crvBXH;
        private final TextView tenTruyen;
        private final ImageView img;


        public TruyenTranhViewHolder(@NonNull View itemView) {
            super(itemView);
            crvBXH = itemView.findViewById(R.id.crv_bxh);
            tenTruyen = itemView.findViewById(R.id.bxh_truyen);
            img = itemView.findViewById(R.id.bxh_anhtruyen);
        }
    }
}

