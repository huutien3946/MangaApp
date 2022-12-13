package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TaiKhoan implements Serializable {
    private String _id;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private boolean PhanQuyen;
    private boolean TrangThai;
    private String[] BinhLuans;
    private List YeuThich;
    private List LichSu;
    private Date NgayTao;
    private String HoTen;


    public TaiKhoan(boolean trangThai, List yeuThich, List lichSu) {
        TrangThai = trangThai;
        YeuThich = yeuThich;
        LichSu = lichSu;
    }

    public TaiKhoan(String taiKhoan, String email, String hoTen) {
        TaiKhoan = taiKhoan;
        Email = email;
        HoTen = hoTen;
    }


    public TaiKhoan(String taiKhoan, String matKhau, String email, String hoTen) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        Email = email;
        HoTen = hoTen;
    }

    public TaiKhoan(String matKhau) {
        MatKhau = matKhau;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(boolean phanQuyen, boolean trangThai, String hoTen) {
        PhanQuyen = phanQuyen;
        TrangThai = trangThai;
        HoTen = hoTen;
    }


    public TaiKhoan(String taiKhoan, String matKhau) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(boolean phanQuyen) {
        PhanQuyen = phanQuyen;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String[] getBinhLuans() {
        return BinhLuans;
    }

    public void setBinhLuans(String[] binhLuans) {
        BinhLuans = binhLuans;
    }

    public List getYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(List yeuThich) {
        YeuThich = yeuThich;
    }

    public List getLichSu() {
        return LichSu;
    }

    public void setLichSu(List lichSu) {
        LichSu = lichSu;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date ngayTao) {
        NgayTao = ngayTao;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "_id='" + _id + '\'' +
                ", TaiKhoan='" + TaiKhoan + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", Email='" + Email + '\'' +
                ", PhanQuyen=" + PhanQuyen +
                ", TrangThai=" + TrangThai +
                ", BinhLuans=" + Arrays.toString(BinhLuans) +
                ", YeuThich=" + YeuThich +
                ", LichSu=" + LichSu +
                ", NgayTao=" + NgayTao +
                ", HoTen='" + HoTen + '\'' +
                '}';
    }

}