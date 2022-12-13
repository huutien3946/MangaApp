package com.example.mangaapp.display;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSu extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    String id = null;
    private RecyclerView recyclerView;
    private TruyenTranhAdapter truyenTranhAdapter;
    private List<Truyen> listTruyen;
    private List<Truyen> list = new ArrayList<>();
    private Truyen truyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_lich_su);
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        id = sharedPreferences.getString("value", "");
        init();
        GetTruyen();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(LichSu.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

    }

    private void init() {
        recyclerView = findViewById(R.id.rcv_lichsu);
    }

    private void GetTruyen() {
        ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                List LichSu = taiKhoan.getLichSu();
                ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                        listTruyen = response.body();
                        if (listTruyen != null) {
                            for (int i = 0; i < listTruyen.size(); i++) {
                                for (int j = 0; j < LichSu.size(); j++) {
                                    if (listTruyen.get(i).get_id().equals(LichSu.get(j))) {
                                        list.add(listTruyen.get(i));
                                    }
                                }
                            }
                            truyenTranhAdapter = new TruyenTranhAdapter(LichSu.this, list);
                            recyclerView.setAdapter(truyenTranhAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
                        Log.e("Lỗi: ", t.toString());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                Log.e("Lỗi: ", t.toString());
            }
        });
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}