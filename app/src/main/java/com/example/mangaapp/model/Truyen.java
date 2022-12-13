package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Truyen implements Serializable {
    private String _id;
    private String TenTruyen;
    private String[] TheLoais;
    private boolean TrangThai;
    private boolean TinhTrang;
    private String GioiThieu;
    private String AnhBia;
    private int LuotThich;
    private int LuotXem;
    private int LuotXemThang;
    private Date NgayXepHang;
    private String[] TacGias;
    private Chapter[] Chapters;

    public Truyen(boolean trangThai, boolean tinhTrang, int luotThich, int luotXem, int luotXemThang, Date ngayXepHang) {
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        LuotThich = luotThich;
        LuotXem = luotXem;
        LuotXemThang = luotXemThang;
        NgayXepHang = ngayXepHang;
    }

    public Truyen(boolean trangThai, boolean tinhTrang, int luotXemThang, Date ngayXepHang) {
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        LuotXemThang = luotXemThang;
        NgayXepHang = ngayXepHang;
    }

    public Truyen(boolean trangThai, boolean tinhTrang, int luotXem) {
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        LuotXem = luotXem;
    }

    public Truyen(boolean trangThai, boolean tinhTrang, int luotXem, int luotXemThang, Date ngayXepHang) {
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        LuotXem = luotXem;
        LuotXemThang = luotXemThang;
        NgayXepHang = ngayXepHang;
    }

    public Truyen(String _id, String tenTruyen, String[] theLoais, boolean trangThai, boolean tinhTrang, String gioiThieu, String anhBia, int luotThich, int luotXem, int luotXemThang, Date ngayXepHang, String[] tacGias, Chapter[] chapters) {
        this._id = _id;
        TenTruyen = tenTruyen;
        TheLoais = theLoais;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        GioiThieu = gioiThieu;
        AnhBia = anhBia;
        LuotThich = luotThich;
        LuotXem = luotXem;
        LuotXemThang = luotXemThang;
        NgayXepHang = ngayXepHang;
        TacGias = tacGias;
        Chapters = chapters;
    }


    public Truyen(boolean trangThai, boolean tinhTrang, int luotThich, int luotXem) {
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        LuotThich = luotThich;
        LuotXem = luotXem;
    }

    public Truyen() {
    }


    @Override
    public String toString() {
        return "Truyen{" +
                "_id='" + _id + '\'' +
                ", TenTruyen='" + TenTruyen + '\'' +
                ", TheLoais=" + Arrays.toString(TheLoais) +
                ", TrangThai=" + TrangThai +
                ", TinhTrang=" + TinhTrang +
                ", GioiThieu='" + GioiThieu + '\'' +
                ", AnhBia='" + AnhBia + '\'' +
                ", LuotThich=" + LuotThich +
                ", LuotXem=" + LuotXem +
                ", LuotXemThang=" + LuotXemThang +
                ", NgayXepHang=" + NgayXepHang +
                ", TacGias=" + Arrays.toString(TacGias) +
                ", Chapters=" + Arrays.toString(Chapters) +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String[] getTheLoais() {
        return TheLoais;
    }

    public void setTheLoais(String[] theLoais) {
        TheLoais = theLoais;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
    }

    public String getAnhBia() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        AnhBia = anhBia;
    }

    public int getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(int luotThich) {
        LuotThich = luotThich;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public int getLuotXemThang() {
        return LuotXemThang;
    }

    public void setLuotXemThang(int luotXemThang) {
        LuotXemThang = luotXemThang;
    }

    public Date getNgayXepHang() {
        return NgayXepHang;
    }

    public void setNgayXepHang(Date ngayXepHang) {
        NgayXepHang = ngayXepHang;
    }

    public String[] getTacGias() {
        return TacGias;
    }

    public void setTacGias(String[] tacGias) {
        TacGias = tacGias;
    }

    public Chapter[] getChapters() {
        return Chapters;
    }

    public void setChapters(Chapter[] chapters) {
        Chapters = chapters;
    }
}