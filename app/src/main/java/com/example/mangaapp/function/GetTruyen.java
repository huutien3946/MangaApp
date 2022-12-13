package com.example.mangaapp.function;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.ChapterAdapter;
import com.example.mangaapp.adapter.TacGiaAdapter;
import com.example.mangaapp.adapter.TheLoaiAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.TacGia;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.TheLoai;
import com.example.mangaapp.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTruyen extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private final Context context = this;
    String id = null;
    private TextView tvTenTruyen, tvTinhTrang, tv_luotxem, tvLike, tvNoiDung, tvTongChuong;
    private ImageView imgAnhBia, imgAnhNen, fav;
    private RecyclerView rcvTheLoai, rcvTacGia, rcvChapter;
    private List<String> listIDTheLoai, listIDTacGia;
    private List<TheLoai> listTheLoai;
    private List<TacGia> listTacGia;
    private List YeuThich;
    private List LichSu;
    private ChapterAdapter chapterAdapter;
    private boolean isfav = true;
    private boolean isID = true;

    List<Chapter> mlistChapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_truyen);
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        id = sharedPreferences.getString("value", "");
        // lấy intent
        Intent intent = getIntent();
        Truyen truyen = (Truyen) intent.getSerializableExtra("clickTruyen");
        init();
        listIDTheLoai = new ArrayList<>();
        listTheLoai = new ArrayList<>();
        listIDTacGia = new ArrayList<>();
        listTacGia = new ArrayList<>();
        initLinearLayout();
        hienThiTruyen(truyen);
        themLichSu(truyen);
        isFavorite(truyen);
        fav.setOnClickListener(v -> {
            check(id);
            if (isID) {
                if (isfav) {
                    xoaYeuThich(truyen);
                } else {
                    themYeuThich(truyen);
                }
            }
        });
    }

    private void check(String id) {
        if (id.equals("")) {
            Dialog();
            isID = false;
        } else {
            ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
                @Override
                public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                    TaiKhoan taiKhoan = response.body();
                    if (taiKhoan != null) {
                        if (!taiKhoan.isTrangThai()) {
                            Dialog3();
                            isID = false;
                        } else {
                            isID = true;
                            LichSu = taiKhoan.getLichSu();
                            YeuThich = taiKhoan.getYeuThich();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                    Log.e("Thông tin tài khoản: ", t.toString());
                }
            });
        }
    }

    private void isFavorite(Truyen truyen) {
        ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                if (taiKhoan != null) {
                    List YeuThich = taiKhoan.getYeuThich();
                    if (YeuThich.contains(truyen.get_id())) {
                        fav.setImageResource(R.drawable.ic_favorite_red);
                        fav.setBackgroundResource(R.drawable.ic_favorite_red);
                        isfav = true;
                    } else {
                        fav.setImageResource(R.drawable.ic_favorite_black);
                        fav.setBackgroundResource(R.drawable.ic_favorite_black);
                        isfav = false;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {

            }
        });
    }

    private void initLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        rcvTheLoai.setLayoutManager(gridLayoutManager);
        rcvTacGia.setLayoutManager(linearLayoutManager);
        rcvChapter.setLayoutManager(linearLayoutManager2);
    }

    @SuppressLint("SetTextI18n")
    private void hienThiTruyen(Truyen truyen) {
        if (truyen != null && truyen.isTrangThai()) {
            listIDTheLoai = Arrays.asList(truyen.getTheLoais());
            listIDTacGia = Arrays.asList(truyen.getTacGias());
            mlistChapter = Arrays.asList(truyen.getChapters());
            //Hiển thị thể loại
            for (int i = 0; i < listIDTheLoai.size(); i++) {
                ApiService.apiService.GetTheLoai(listIDTheLoai.get(i)).enqueue(new Callback<TheLoai>() {
                    @Override
                    public void onResponse(@NonNull Call<TheLoai> call, @NonNull Response<TheLoai> response) {
                        TheLoai theLoai = response.body();
                        if (theLoai != null && theLoai.isTrangThai()) {
                            listTheLoai.add(theLoai);
                        }
                        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(listTheLoai, GetTruyen.this);
                        rcvTheLoai.setAdapter(theLoaiAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<TheLoai> call, @NonNull Throwable t) {
                    }
                });
            }
            //Hiển thị tác giả
            for (int i = 0; i < listIDTacGia.size(); i++) {
                ApiService.apiService.GetTacGia(listIDTacGia.get(i)).enqueue(new Callback<TacGia>() {
                    @Override
                    public void onResponse(@NonNull Call<TacGia> call, @NonNull Response<TacGia> response) {
                        TacGia tacGia = response.body();
                        if (tacGia != null && tacGia.isTrangThai()) {
                            listTacGia.add(tacGia);
                        }
                        TacGiaAdapter tacGiaAdapter = new TacGiaAdapter(listTacGia, context);
                        rcvTacGia.setAdapter(tacGiaAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<TacGia> call, @NonNull Throwable t) {

                    }
                });
            }
            //Hiển thị chapter
            Collections.reverse(mlistChapter);
            chapterAdapter = new ChapterAdapter(mlistChapter, context);
            rcvChapter.setAdapter(chapterAdapter);
            //hiển thị thông tin truyện
            tvTenTruyen.setText(truyen.getTenTruyen());
            if (truyen.isTrangThai()) {
                tvTinhTrang.setText("Tình trạng: Hoàn thành");
            } else {
                tvTinhTrang.setText("Tình trạng: Đang tiến thành");
            }
            tvNoiDung.setText(truyen.getGioiThieu());
            tvTongChuong.setText("Tổng chapter: " + mlistChapter.size());
            Picasso.get().load(truyen.getAnhBia()).into(imgAnhBia);
            Picasso.get().load(truyen.getAnhBia()).into(imgAnhNen);
            updateLuotXem(truyen.get_id());
            update(truyen);
        }
    }

    private void updateLuotXem(String truyen) {
        ApiService.apiService.GetTruyen(truyen).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                Truyen truyen1 = response.body();
                List<Chapter> listChapter = Arrays.asList(truyen1.getChapters());
                int sum = 0;
                for (int i = 0; i < listChapter.size(); i++) {
                    sum = sum + listChapter.get(i).getLuotXem();
                }
                Truyen truyen2 = new Truyen(true, true, truyen1.getLuotThich(), sum, truyen1.getLuotXemThang(), truyen1.getNgayXepHang());
                ApiService.apiService.UpdateTruyen(truyen, truyen2).enqueue(new Callback<Truyen>() {
                    @Override
                    public void onResponse(Call<Truyen> call, Response<Truyen> response) {

                    }

                    @Override
                    public void onFailure(Call<Truyen> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {

            }
        });
    }

    private void update(@NonNull Truyen truyen) {
        ApiService.apiService.GetTruyen(truyen.get_id()).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                Truyen truyen1 = response.body();
                if (truyen1 != null && truyen1.isTrangThai()) {
                    String luotxem = String.valueOf(truyen1.getLuotXem());
                    String luotthich = String.valueOf(truyen1.getLuotThich());
                    tv_luotxem.setText(luotxem);
                    tvLike.setText(luotthich);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
            }
        });
    }

    private void themLichSu(Truyen truyen) {
        ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                if (taiKhoan != null && taiKhoan.isTrangThai()) {
                    LichSu = taiKhoan.getLichSu();
                    YeuThich = taiKhoan.getYeuThich();
                    if (!LichSu.contains(truyen.get_id())) {
                        LichSu.add(truyen.get_id());
                    }
                    TaiKhoan taiKhoan1 = new TaiKhoan(taiKhoan.isTrangThai(), YeuThich, LichSu);
                    ApiService.apiService.updateTaiKhoan(id, taiKhoan1).enqueue(new Callback<TaiKhoan>() {
                        @Override
                        public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                        }

                        @Override
                        public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {

            }
        });
    }

    private void themYeuThich(@NonNull Truyen truyen) {
        if (!YeuThich.contains(truyen.get_id())) {
            YeuThich.add(truyen.get_id());
        }
        Log.e("trung: ", "vao if"+ YeuThich);
        TaiKhoan taiKhoan = new TaiKhoan(true, YeuThich, LichSu);
        ApiService.apiService.updateTaiKhoan(id, taiKhoan).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {

            }

            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {

            }
        });
        themLuotThich(truyen);
    }

    private void themLuotThich(@NonNull Truyen truyen) {
        ApiService.apiService.GetTruyen(truyen.get_id()).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                Truyen truyen1 = response.body();
                if (truyen1 != null) {
                    int temp = truyen1.getLuotThich();
                    temp += 1;

                    int finalTemp = temp;
                    Truyen truyen2 = new Truyen(true, truyen.isTinhTrang(), temp, truyen.getLuotXem(), truyen.getLuotXemThang(), truyen.getNgayXepHang());
                    ApiService.apiService.UpdateTruyen(truyen.get_id(), truyen2).enqueue(new Callback<Truyen>() {
                        @Override
                        public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                        }

                        @Override
                        public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
                            String luotthich = String.valueOf(finalTemp);
                            tvLike.setText(luotthich);
                        }
                    });
                }
                fav.setImageResource(R.drawable.ic_favorite_red);
                fav.setBackgroundResource(R.drawable.ic_favorite_red);
                isfav = true;

            }

            @Override
            public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
                Intent intent1 = getIntent();
                finish();
                startActivity(intent1);
            }
        });
    }

    private void xoaYeuThich(@NonNull Truyen truyen) {
        if (YeuThich.contains(truyen.get_id())) {
            YeuThich.remove(truyen.get_id());
        }
        TaiKhoan taiKhoan = new TaiKhoan(true, YeuThich, LichSu);
        ApiService.apiService.updateTaiKhoan(id, taiKhoan).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {

            }

            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {

            }
        });
        xoaLuotThich(truyen);
    }

    private void xoaLuotThich(@NonNull Truyen truyen) {
        ApiService.apiService.GetTruyen(truyen.get_id()).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                Truyen truyen1 = response.body();
                if (truyen1 != null) {
                    int temp = truyen1.getLuotThich();
                    temp -= 1;
                    Truyen truyen2 = new Truyen(true, truyen.isTinhTrang(), temp, truyen.getLuotXem(), truyen.getLuotXemThang(), truyen.getNgayXepHang());
                    Log.e("xoa", "" + truyen2.getLuotThich());
                    int finalTemp = temp;
                    ApiService.apiService.UpdateTruyen(truyen.get_id(), truyen2).enqueue(new Callback<Truyen>() {
                        @Override
                        public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                            String luotthich = String.valueOf(finalTemp);
                            tvLike.setText(luotthich);
                        }

                        @Override
                        public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
                            String luotthich = String.valueOf(finalTemp);
                            tvLike.setText(luotthich);
                        }
                    });
                }
                fav.setImageResource(R.drawable.ic_favorite_black);
                fav.setBackgroundResource(R.drawable.ic_favorite_black);
                isfav = false;
            }

            @Override
            public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        Truyen truyen = (Truyen) intent.getSerializableExtra("clickTruyen");
        update(truyen);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (chapterAdapter != null)
            chapterAdapter.release();
    }


    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        tvTenTruyen = findViewById(R.id.tv_ten_truyen);
        fav = findViewById(R.id.btn_fav);
        tvTinhTrang = findViewById(R.id.tv_tinhtrang_truyen);
        tv_luotxem = findViewById(R.id.tv_luotxem);
        tvLike = findViewById(R.id.tv_like);
        tvNoiDung = findViewById(R.id.tv_noi_dung_truyen);
        tvTongChuong = findViewById(R.id.tv_tong_chapter);
        imgAnhBia = findViewById(R.id.manga_cover);
        imgAnhNen = findViewById(R.id.backdrop);
        rcvTheLoai = findViewById(R.id.rcv_The_Loai);
        rcvTacGia = findViewById(R.id.rcv_tac_gia);
        rcvChapter = findViewById(R.id.rcv_chapter);
    }

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tài khoản chưa đăng nhập")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(((Dialog) dialog).getContext(), SignIn.class);
            startActivity(intent);
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Dialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tài khoản đã bị đóng băng! Xin liên hệ quản trị viên ")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}