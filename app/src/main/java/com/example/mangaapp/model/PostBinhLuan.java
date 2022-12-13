package com.example.mangaapp.model;

import java.util.Date;

public class PostBinhLuan {
    private String NoiDungBL;
    private boolean TrangThai;
    private Date NgayNhap;
    private String Chapter;
    private String TaiKhoan;

    public PostBinhLuan() {
    }

    public PostBinhLuan(String noiDungBL, boolean trangThai, String chapter, String taiKhoan) {
        NoiDungBL = noiDungBL;
        TrangThai = trangThai;
        Chapter = chapter;
        TaiKhoan = taiKhoan;
    }

    public String getNoiDungBL() {
        return NoiDungBL;
    }

    public void setNoiDungBL(String noiDungBL) {
        NoiDungBL = noiDungBL;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public String getChapter() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        Chapter = chapter;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }
}
