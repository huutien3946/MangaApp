package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.function.GetChapter;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.Truyen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private final List<Chapter> mListChapter;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public ChapterAdapter(List<Chapter> mListChapter, Context context) {
        this.mListChapter = mListChapter;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = mListChapter.get(position);
        if (chapter == null) {
            return;
        }
        holder.tvTenChapter.setText(chapter.getTenChapter());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvNgayNhap.setText("" + simpleDateFormat.format(chapter.getNgayNhap()));
        ApiService.apiService.GetChapter(chapter.get_id()).enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
                Chapter chapter1 = response.body();
                if (chapter1 != null) {
                    holder.tvXem.setText("" + chapter1.getLuotXem());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {

            }
        });
        holder.cvChapter.setOnClickListener(v -> {
            Intent intent = new Intent(context, GetChapter.class);
            intent.putExtra("clickchapter", chapter);
            intent.putExtra("clickitem", position);
            context.startActivity(intent);
        });
    }

    public void release() {
        context = null;
    }

    @Override
    public int getItemCount() {
        if (mListChapter != null)
            return mListChapter.size();
        return 0;
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTenChapter;
        private final TextView tvXem;
        private final TextView tvNgayNhap;
        private final CardView cvChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenChapter = itemView.findViewById(R.id.tv_item_ten_chapter);
            tvNgayNhap = itemView.findViewById(R.id.tv_ngay_dang_chapter);
            cvChapter = itemView.findViewById(R.id.cv_chapter);
            tvXem = itemView.findViewById(R.id.tv_luot_xem);
        }
    }
}