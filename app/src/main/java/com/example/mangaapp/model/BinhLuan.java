package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Date;

public class BinhLuan implements Serializable {


    private String NoiDungBL;
    private boolean TrangThai;
    private Date NgayNhap;
    private String Chapter;
    private TaiKhoan TaiKhoan;

    public BinhLuan() {
    }

    public BinhLuan(String noiDungBL, boolean trangThai, Date ngayNhap, String chapter, com.example.mangaapp.model.TaiKhoan taiKhoan) {
        NoiDungBL = noiDungBL;
        TrangThai = trangThai;
        NgayNhap = ngayNhap;
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

    public com.example.mangaapp.model.TaiKhoan getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(com.example.mangaapp.model.TaiKhoan taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    @Override
    public String toString() {
        return "BinhLuan{" +
                "NoiDungBL='" + NoiDungBL + '\'' +
                ", TrangThai=" + TrangThai +
                ", NgayNhap=" + NgayNhap +
                ", Chapter='" + Chapter + '\'' +
                ", TaiKhoan=" + TaiKhoan +
                '}';
    }
}
