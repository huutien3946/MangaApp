package com.example.mangaapp.function;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.display.ThongTinTaiKhoan;
import com.example.mangaapp.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private Button dangky, quaylai;
    private TextInputEditText matkhau, tentaikhoan, nhaplaimatkhau, email, ten;
    private List<TaiKhoan> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_sign_up);
        init();
        getTaiKhoan();
        quaylai.setOnClickListener(v -> clickQuayLai());
        dangky.setOnClickListener(v -> clickDangKy());
    }

    private void clickDangKy() {
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String hoten = Objects.requireNonNull(ten.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        String mail = Objects.requireNonNull(email.getText()).toString().trim();
        if (Validation()) {
            TaiKhoan taikhoan = new TaiKhoan(name, pass, mail, hoten);
            ApiService.apiService.PostTaiKhoan(taikhoan).enqueue(new Callback<TaiKhoan>() {
                @Override
                public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                    TaiKhoan taiKhoan1 = response.body();
                    if (taiKhoan1 != null) {
                        SharedPreferences sharedPref = getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value", taiKhoan1.get_id());
                        editor.apply();
                        Dialog();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                }
            });
        }
    }

    private boolean Validation() {
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        String hoten = Objects.requireNonNull(ten.getText()).toString().trim();
        String repass = Objects.requireNonNull(nhaplaimatkhau.getText()).toString().trim();
        String mail = Objects.requireNonNull(email.getText()).toString().trim();
        if (TextUtils.isEmpty(mail)) {
            email.setError("Email kh??ng ???????c ????? tr???ng");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Email kh??ng ????ng ?????nh d???ng");
            email.requestFocus();
            return false;
        }
        for (TaiKhoan taiKhoan : list) {
            if (mail.equals(taiKhoan.getEmail())) {
                email.setError("Email ???? ???????c s??? d???ng");
                email.requestFocus();
                return false;
            }
        }
        if (TextUtils.isEmpty(name)) {
            tentaikhoan.setError("T??n t??i kho???n kh??ng ???????c ????? tr???ng");
            tentaikhoan.requestFocus();
            return false;
        }
        if (name.length() < 6 || name.length() > 20) {
            tentaikhoan.setError("T??n t??i kho???n kh??ng ???????c ??t h??n 6 k?? t??? v?? l???n h??n 20 k?? t???");
            tentaikhoan.requestFocus();
            return false;
        }
        for (TaiKhoan taiKhoan : list) {
            if (name.equals(taiKhoan.getTaiKhoan())) {
                tentaikhoan.setError("T??n t??i kho???n ???? ???????c s??? d???ng");
                tentaikhoan.requestFocus();
                return false;
            }
        }
        if (TextUtils.isEmpty(hoten)) {
            ten.setError("T??n kh??ng ???????c ????? tr???ng");
            ten.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            matkhau.setError("M???t kh???u kh??ng ???????c ????? tr???ng");
            matkhau.requestFocus();
            return false;
        }
        if (pass.length() < 6) {
            matkhau.setError("M???t kh???u kh??ng ???????c ??t h??n 6 k?? t???");
            matkhau.requestFocus();
            return false;
        }
        if (!repass.equals(pass)) {
            nhaplaimatkhau.setError("M???t kh???u kh??ng tr??ng kh???p");
            nhaplaimatkhau.requestFocus();
            return false;
        }
        return true;
    }

    private void clickQuayLai() {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void init() {
        tentaikhoan = findViewById(R.id.edt_TenTaiKhoan);
        ten = findViewById(R.id.edt_Ten);
        email = findViewById(R.id.edt_Email);
        matkhau = findViewById(R.id.edt_MatKhau);
        nhaplaimatkhau = findViewById(R.id.edt_NhapLaiMatKhau);
        quaylai = findViewById(R.id.btn_QuayLai);
        dangky = findViewById(R.id.btn_DangKy);
    }

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("T???o t??i kho???n th??nh c??ng")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(((Dialog) dialog).getContext(), SignIn.class);
            startActivity(intent);
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getTaiKhoan() {
        ApiService.apiService.GetTaiKhoan().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaiKhoan>> call, @NonNull Response<List<TaiKhoan>> response) {
                list = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<List<TaiKhoan>> call, @NonNull Throwable t) {
                Log.e("l???i ??? ????ng k??", t.toString());
            }
        });
    }

}