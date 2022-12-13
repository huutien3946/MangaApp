package com.example.mangaapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.function.SignIn;
import com.example.mangaapp.model.BinhLuan;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.PostBinhLuan;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private final List<Chapter> listChapter;
    private List<Chapter> listAllChapter;
    private Context context;

    public DetailAdapter(List<Chapter> listChapter, Context context) {
        this.listChapter = listChapter;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        Chapter chapter = listChapter.get(position);
        if (chapter == null) {
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        List anh = new ArrayList();
        List<BinhLuan> binhluan = new ArrayList();
        List<BinhLuan> binhluan1 = new ArrayList();
        anh = Arrays.asList(chapter.getLinkAnhs());
        binhluan = Arrays.asList(chapter.getBinhLuans());
        for (int i = 0; i < binhluan.size(); i++) {
            if(binhluan.get(i).isTrangThai()){
                binhluan1.add(binhluan.get(i));
            }
        }
        holder.tv_chapter.setText(chapter.getTenChapter());
        //
        LinkAnhAdapter linkAnhAdapter = new LinkAnhAdapter(anh);
        holder.rcv_anh.setAdapter(linkAnhAdapter);
        holder.rcv_anh.setLayoutManager(linearLayoutManager);
        //
        BinhLuanAdapter binhLuanAdapter = new BinhLuanAdapter(binhluan1);
        holder.rcv_binhluan.setAdapter(binhLuanAdapter);
        holder.rcv_binhluan.setLayoutManager(linearLayoutManager1);
        //
        Chapter updateChap = new Chapter((chapter.getLuotXem() + 1), true);
        ApiService.apiService.UpdateChapter(chapter.get_id(), updateChap).enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {
            }
        });
        UpdateLuotXemTruyen(chapter.getTruyen());
        //updateLuotXem(chapter.getTruyen());
        //
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences(MY_PREFERENCE_NAME, holder.itemView.getContext().MODE_PRIVATE);
        String id = sharedPreferences.getString("value", "");
        holder.btn_ThemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.equals("")) {
                    Dialog1();
                } else {
                    String text = holder.edt_ThemBinhLuan.getText().toString().trim();
                    PostBinhLuan postBinhLuan = new PostBinhLuan(text, true, chapter.get_id(), id);
                    ApiService.apiService.ThemBinhLuan(postBinhLuan).enqueue(new Callback<PostBinhLuan>() {
                        @Override
                        public void onResponse(Call<PostBinhLuan> call, Response<PostBinhLuan> response) {
                        }

                        @Override
                        public void onFailure(Call<PostBinhLuan> call, Throwable t) {
                        }
                    });
                    Dialog();
                }

            }
        });
    }

    private void UpdateLuotXemTruyen(String truyen) {
        ApiService.apiService.GetTruyen(truyen).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                Truyen truyen1 = response.body();
                if (truyen1 != null) {
                    int luotxemThang = truyen1.getLuotXemThang();
                    Date ngayXepHang = truyen1.getNgayXepHang();
                    int thang = ngayXepHang.getMonth() + 1;
                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    int currentMonth = calendar.get(Calendar.MONTH) + 1;
                    Date currentTime = Calendar.getInstance().getTime();
                    if (thang == currentMonth) {
                        luotxemThang += 1;
                    } else {
                        luotxemThang = 1;
                        ngayXepHang = currentTime;
                    }
                    Truyen truyen2 = new Truyen(truyen1.isTrangThai(), truyen1.isTinhTrang(), truyen1.getLuotThich(), (truyen1.getLuotXem()+1), luotxemThang, ngayXepHang);
                    ApiService.apiService.UpdateTruyen(truyen1.get_id(), truyen2).enqueue(new Callback<Truyen>() {
                        @Override
                        public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {

                        }

                        @Override
                        public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        if (listChapter != null) {
            return listChapter.size();
        }
        return 0;
    }

    private void Dialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn chưa đăng nhập! Chuyển đến trang đăng nhập")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(context, SignIn.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        builder.setNegativeButton("CANCEL", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bình luận thành công")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = ((Activity) context).getIntent();
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_chapter;
        private final EditText edt_ThemBinhLuan;
        private final Button btn_ThemBinhLuan;
        private final RecyclerView rcv_anh;
        private final RecyclerView rcv_binhluan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chapter = itemView.findViewById(R.id.tv_chapter);
            rcv_anh = itemView.findViewById(R.id.rcv_linkanh);
            rcv_binhluan = itemView.findViewById(R.id.rcv_binhluan);
            edt_ThemBinhLuan = itemView.findViewById(R.id.edt_ThemBinhLuan);
            btn_ThemBinhLuan = itemView.findViewById(R.id.btn_ThemBinhLuan);
        }
    }
}
