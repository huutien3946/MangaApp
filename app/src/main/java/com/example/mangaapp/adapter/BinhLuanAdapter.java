package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.model.BinhLuan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanApdaterViewHolder> {

    private List<BinhLuan> listBinhLuan;

    public BinhLuanAdapter(List<BinhLuan> mListBinhLuan) {
        this.listBinhLuan = mListBinhLuan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BinhLuanApdaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_binh_luan, parent, false);
        return new BinhLuanAdapter.BinhLuanApdaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanApdaterViewHolder holder, int position) {
        BinhLuan binhLuan = listBinhLuan.get(position);
        if (binhLuan == null) {
            return;
        }
        holder.tvTenTaiKhoan.setText(binhLuan.getTaiKhoan().getTaiKhoan()); // tai khoan dau la cua Binh Luan. tai khoan lan sau la ten dangnhap
        Date ngaynhap = binhLuan.getNgayNhap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvNgayNhap.setText("Ngày đăng: " + simpleDateFormat.format(ngaynhap));
        holder.tvNoiDung.setText(binhLuan.getNoiDungBL().trim());
    }

    @Override
    public int getItemCount() {
        if (listBinhLuan != null) {
            return listBinhLuan.size();
        }
        return 0;
    }


    public class BinhLuanApdaterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTaiKhoan, tvNgayNhap, tvNoiDung;


        public BinhLuanApdaterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTaiKhoan = itemView.findViewById(R.id.tv_tenTaiKhoan);
            tvNgayNhap = itemView.findViewById(R.id.tv_ngayNhap);
            tvNoiDung = itemView.findViewById(R.id.tv_noi_dung);
        }
    }
}