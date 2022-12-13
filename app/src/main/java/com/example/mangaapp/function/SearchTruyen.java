package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTruyen extends AppCompatActivity {

    private RecyclerView rcvDSTruyenTimKiem;
    private TruyenTranhAdapter truyenTranhAdapter;
    private List<Truyen> listTruyen;
    private EditText edtTimKiem;
    private ImageView imgSearch, refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_search_truyen);
        Intent intent = getIntent();
        String tenTacGia = (String) intent.getSerializableExtra("clickTenTacGia");
        String tenTheLoai = (String) intent.getSerializableExtra("clickTenTheLoai");
        init();
        if (tenTacGia != null) {
            getTaCaTruyenTheoTacGia(tenTacGia);
        } else {
            if (tenTheLoai != null) {
                getTaCaTruyenTheoTheLoai(tenTheLoai);
            } else {
                GetTatCaTruyen();
            }
        }
        imgSearch.setOnClickListener(v -> GetTruyenTheoTenOrTacGia(edtTimKiem.getText().toString().trim()));
        refresh.setOnClickListener(v -> {
            edtTimKiem.setText("");
            GetTatCaTruyen();
        });
    }

    //Full màn hình
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void GetTruyenTheoTenOrTacGia(String id) {
        String key = edtTimKiem.getText().toString().trim();
        if (key.isEmpty())
            GetTatCaTruyen();
        else {
            ApiService.apiService.GetTruyenTheoTenTruyenOrTacGia(id).enqueue(new Callback<List<Truyen>>() {
                @Override
                public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                    listTruyen = response.body();
                    truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, listTruyen);
                    rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {

                }
            });
        }

    }

    private void getTaCaTruyenTheoTheLoai(String tenTheLoai) {
        ApiService.apiService.GetTruyenTheoTheLoai(tenTheLoai).enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                listTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, listTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
                Toast.makeText(SearchTruyen.this, "Get truyen theo tac gia that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        refresh = findViewById(R.id.ig_refresh);
        edtTimKiem = findViewById(R.id.et_TimKiem);
        imgSearch = findViewById(R.id.img_search);
        rcvDSTruyenTimKiem = findViewById(R.id.rcv_DSTruyen_TimKiem);
        listTruyen = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvDSTruyenTimKiem.setLayoutManager(gridLayoutManager);
        rcvDSTruyenTimKiem.setNestedScrollingEnabled(false);
        rcvDSTruyenTimKiem.setFocusable(false);
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String keysearch) {
        ArrayList<Truyen> filterlist = new ArrayList<>();
        for (Truyen item : listTruyen) {
            if (item.getTenTruyen().toLowerCase().contains(keysearch.toLowerCase())) {
                filterlist.add(item);
            }
        }
        truyenTranhAdapter.filterList(filterlist);
    }

    private void getTaCaTruyenTheoTacGia(String tenTacGia) {
        ApiService.apiService.GetTruyenTheoTacGia(tenTacGia).enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                listTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, listTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
            }
        });
    }


    private void GetTatCaTruyen() {
        ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                listTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, listTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
            }
        });
    }


}