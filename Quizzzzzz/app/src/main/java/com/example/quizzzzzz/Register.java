package com.example.quizzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.quizzzzzz.NetworkUtils.BASE_URL;
import static com.example.quizzzzzz.NetworkUtils.URI_NGUOI_CHOI_THEM;

public class Register extends AppCompatActivity {
    EditText editTenDangNhap, editEmail, editPass, editRepass;
    Button btnDangKy;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
    public static final String COLUMN_MAT_KHAU = "mat_khau";
    public static final String COLUMN_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AnhXa();

    }
    private void ThemTk(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
    private void AnhXa(){
        btnDangKy = findViewById(R.id.btn_register);
        editTenDangNhap = findViewById(R.id.editText_username);
        editEmail = findViewById(R.id.editText_email);
        editPass = findViewById(R.id.editText_password);
        editRepass = findViewById(R.id.editText_re_passowrd);
    }

    public void dangKy(View view) {
        final String tenDangNhap = editTenDangNhap.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        final String matKhau = editPass.getText().toString().trim();
        String xacNhanMatKhau = editRepass.getText().toString().trim();

        if (tenDangNhap.equals("") || email.equals("") || matKhau.equals("") || xacNhanMatKhau.equals("")) {
            Toast.makeText(this, "Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        } else {

            if (!matKhau.equals(xacNhanMatKhau)){
                Toast.makeText(this, "Mật khẩu không giống nhau",Toast.LENGTH_SHORT).show();
            }else{
                final ProgressDialog pgwait = NetworkUtils.showProress(this);
                pgwait.show();
                StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + URI_NGUOI_CHOI_THEM, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pgwait.dismiss();
                            JSONObject objLogin = new JSONObject(response);
                            boolean result = objLogin.getBoolean("success");
                            if (result) {
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                Toast.makeText(Register.this,tenDangNhap+" Tạo Thành Công", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Tạo không thành công Hoặc User Tồn Tại", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,"Server Offline",Toast.LENGTH_SHORT).show();
                        pgwait.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(COLUMN_TEN_DANG_NHAP, tenDangNhap);
                        map.put(COLUMN_EMAIL, email);
                        map.put(COLUMN_MAT_KHAU, matKhau);
                        map.put("Content-Type", "application/json; charset=utf-8");
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(request);
            }
        }
    }
}
