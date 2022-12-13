package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Chapter implements Serializable {

    private String _id;
    private String TenChapter;
    private Date NgayNhap;
    private boolean TrangThai;
    private String Truyen;
    private String[] LinkAnhs;
    private int LuotXem;
    private BinhLuan[] BinhLuans;

    public Chapter() {
    }

    public Chapter(int luotXem, boolean trangThai) {
        LuotXem = luotXem;
        TrangThai = trangThai;
    }

    public Chapter(String _id, String tenChapter, Date ngayNhap, boolean trangThai, String truyen, String[] linkAnhs, int luotXem, BinhLuan[] binhLuans) {
        this._id = _id;
        TenChapter = tenChapter;
        NgayNhap = ngayNhap;
        TrangThai = trangThai;
        Truyen = truyen;
        LinkAnhs = linkAnhs;
        LuotXem = luotXem;
        BinhLuans = binhLuans;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenChapter() {
        return TenChapter;
    }

    public void setTenChapter(String tenChapter) {
        TenChapter = tenChapter;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String getTruyen() {
        return Truyen;
    }

    public void setTruyen(String truyen) {
        Truyen = truyen;
    }

    public String[] getLinkAnhs() {
        return LinkAnhs;
    }

    public void setLinkAnhs(String[] linkAnhs) {
        LinkAnhs = linkAnhs;
    }

    public BinhLuan[] getBinhLuans() {
        return BinhLuans;
    }

    public void setBinhLuans(BinhLuan[] binhLuans) {
        BinhLuans = binhLuans;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "_id='" + _id + '\'' +
                ", TenChapter='" + TenChapter + '\'' +
                ", NgayNhap='" + NgayNhap + '\'' +
                ", TrangThai=" + TrangThai +
                ", Truyen='" + Truyen + '\'' +
                ", LinkAnhs=" + Arrays.toString(LinkAnhs) +
                ", LuotXem=" + LuotXem +
                ", BinhLuans=" + Arrays.toString(BinhLuans) +
                '}';
    }
}
