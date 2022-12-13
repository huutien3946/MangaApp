package com.example.mangaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.function.SearchTruyen;
import com.example.mangaapp.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {


    private Context context;
    private List<TheLoai> mlistTheLoai;

    public TheLoaiAdapter(List<TheLoai> mlistTheLoai, Context context) {
        this.mlistTheLoai = mlistTheLoai;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai, parent, false);
        return new TheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {

        TheLoai theLoai = mlistTheLoai.get(position);
        if (theLoai == null)
            return;
        holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());
        // hien gach chan o duoi text
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchTruyen.class);
                intent.putExtra("clickTenTheLoai", theLoai.getTenTheLoai());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlistTheLoai != null)
            return mlistTheLoai.size();
        return 0;
    }

    public static class TheLoaiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTheLoai;
        private CardView cardView;

        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.crd_theloai);
            tvTenTheLoai = itemView.findViewById(R.id.tv_item_the_loai);
        }
    }
}
