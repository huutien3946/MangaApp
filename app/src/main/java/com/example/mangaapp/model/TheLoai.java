package com.example.mangaapp.model;

import java.io.Serializable;

public class TheLoai implements Serializable {

    private String _id;
    private String TenTheLoai;
    private boolean TrangThai;

    public TheLoai() {
    }

    public TheLoai(String tenTheLoai, boolean trangThai) {
        TenTheLoai = tenTheLoai;
        TrangThai = trangThai;
    }

    public TheLoai(String _id, String tenTheLoai, boolean trangThai) {
        this._id = _id;
        TenTheLoai = tenTheLoai;
        TrangThai = trangThai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "_id='" + _id + '\'' +
                ", TenTheLoai='" + TenTheLoai + '\'' +
                ", TrangThai=" + TrangThai +
                '}';
    }
}
