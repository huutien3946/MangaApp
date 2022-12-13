package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TheLoaiAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.mainscreen.MainScreen;
import com.example.mangaapp.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllTheLoai extends AppCompatActivity {

    private ImageView imgBack, imgSearch;
    private RecyclerView rcvTheloai;
    private TheLoaiAdapter theLoaiAdapter;
    private List<TheLoai> listTheLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_all_the_loai);
        init();
        getTatCaTheLoai();

        imgSearch.setOnClickListener(v -> startActivity(new Intent(GetAllTheLoai.this, SearchTruyen.class)));
        imgBack.setOnClickListener(v -> {
            startActivity(new Intent(GetAllTheLoai.this, MainScreen.class));
            finish();
        });
    }

    //Full màn hình
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        listTheLoai = new ArrayList<>();
        rcvTheloai = findViewById(R.id.rcv_all_theloai);
        imgSearch = findViewById(R.id.img_search);
        imgBack = findViewById(R.id.img_back);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvTheloai.setLayoutManager(gridLayoutManager);
        rcvTheloai.setNestedScrollingEnabled(false);
        rcvTheloai.setFocusable(false);
    }

    private void getTatCaTheLoai() {
        ApiService.apiService.GetTatCaTheLoai().enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(@NonNull Call<List<TheLoai>> call, @NonNull Response<List<TheLoai>> response) {
                listTheLoai = response.body();
                List<TheLoai> list = new ArrayList<>();
                if (listTheLoai != null) {
                    for (int i = 0; i < listTheLoai.size(); i++) {
                        if (listTheLoai.get(i).isTrangThai()) {
                            list.add(listTheLoai.get(i));
                        }
                    }
                    theLoaiAdapter = new TheLoaiAdapter(list, GetAllTheLoai.this);
                    rcvTheloai.setAdapter(theLoaiAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TheLoai>> call, @NonNull Throwable t) {
                Toast.makeText(GetAllTheLoai.this, "get tat ca the loai that bai", Toast.LENGTH_LONG).show();
            }
        });
    }
}