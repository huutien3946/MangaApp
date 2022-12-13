package com.example.mangaapp.model;

import java.io.Serializable;

public class TacGia implements Serializable {
    private String _id;
    private String TenTacGia;
    private boolean TrangThai;

    public TacGia() {
    }

    public TacGia(String tenTacGia, boolean trangThai) {
        TenTacGia = tenTacGia;
        TrangThai = trangThai;
    }

    public TacGia(String _id, String tenTacGia, boolean trangThai) {
        this._id = _id;
        TenTacGia = tenTacGia;
        TrangThai = trangThai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TacGia{" +
                "_id='" + _id + '\'' +
                ", TenTacGia='" + TenTacGia + '\'' +
                ", TrangThai=" + TrangThai +
                '}';
    }
}
