package com.example.mangaapp.function;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.DetailAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetChapter extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private ViewPager2 viewPager2;
    private Chapter chapter;
    private String id;
    private List<Chapter> list = new ArrayList<>();


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_chapter);
        //lấy thông tin từ intent
        Intent intent = getIntent();
        chapter = (Chapter) intent.getSerializableExtra("clickchapter");
        int position = intent.getExtras().getInt("clickitem");
        //ánh xạ
        init();
        //lấy thông tin từ sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        id = sharedPreferences.getString("value", "");
        ApiService.apiService.GetTruyen(chapter.getTruyen()).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                if (response.body() != null) {
                    list = Arrays.asList(response.body().getChapters());
                    Collections.reverse(list);
                    viewPager2.setAdapter(new DetailAdapter(list, GetChapter.this));
                    viewPager2.setCurrentItem(position, false);
                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {

            }
        });
    }

    private void init() {
        viewPager2 = findViewById(R.id.detail_view_pager);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}