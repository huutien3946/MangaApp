package com.example.mangaapp.api;

import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.PostBinhLuan;
import com.example.mangaapp.model.TacGia;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.TheLoai;
import com.example.mangaapp.model.Truyen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    OkHttpClient http = new OkHttpClient().newBuilder().build();
    ApiService apiService = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8000/")
            .baseUrl("https://manga.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(http)
            .build()
            .create(ApiService.class);

    //Tài khoản
    @GET("TaiKhoan")
    Call<List<TaiKhoan>> GetTaiKhoan();

    @POST("TaiKhoan")
    Call<TaiKhoan> PostTaiKhoan(@Body TaiKhoan taiKhoan);

    @GET("TaiKhoan/{id}")
    Call<TaiKhoan> thongtintaikhoan(@Path("id") String ID);

    @PUT("TaiKhoan/{id}")
    Call<TaiKhoan> updateTaiKhoan(@Path("id") String ID, @Body TaiKhoan updateTaiKhoan);

    @POST("TaiKhoan/login")
    Call<TaiKhoan> Login(@Body TaiKhoan taiKhoan);

    @PUT("TaiKhoan/UpdateMatKhau/{id}")
    Call<TaiKhoan> updateMatKHau(@Path("id") String ID, @Body TaiKhoan taiKhoan);

    //Chapter
    @GET("Chapter/{id}")
    Call<Chapter> GetChapter(@Path("id") String ChapterID);

    @PUT("Chapter/{id}")
    Call<Chapter> UpdateChapter(@Path("id") String ChapterID, @Body Chapter chapter);

    //Truyện
    @GET("Truyen")
    Call<List<Truyen>> GetTatCaTruyen();

    @GET("Truyen/TruyenTheoLuotXem")
    Call<List<Truyen>> GetTruyenHot();

    @GET("Truyen/TruyenMoi")
    Call<List<Truyen>> GetTruyenMoi();

    @GET("Truyen/{id}")
    Call<Truyen> GetTruyen(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyenTheoTacGia/{id}")
    Call<List<Truyen>> GetTruyenTheoTacGia(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyenTheoTheLoai/{id}")
    Call<List<Truyen>> GetTruyenTheoTheLoai(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyen/{id}")
    Call<List<Truyen>> GetTruyenTheoTenTruyenOrTacGia(@Path("id") String truyenID);

    @PUT("Truyen/{id}")
    Call<Truyen> UpdateTruyen(@Path("id") String truyenID, @Body Truyen truyen);


    //Thể loại
    @GET("TheLoai/{id}")
    Call<TheLoai> GetTheLoai(@Path("id") String theLoaiID);


    @GET("TheLoai")
    Call<List<TheLoai>> GetTatCaTheLoai();

    //Tác giả
    @GET("TacGia/{id}")
    Call<TacGia> GetTacGia(@Path("id") String tacGiaID);

    //BinhLuan
    @POST("BinhLuan")
    Call<PostBinhLuan> ThemBinhLuan(@Body PostBinhLuan postBinhLuan);

}
