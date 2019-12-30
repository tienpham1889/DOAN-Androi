package com.example.quizzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btnCallActivityLogin;
    private Button btnCallActivityRegster;
    private TextView txtCallActivityForgotPassowrd;
    private SharedPreferences mPref;
    private String sharedPrefFile = "com.example.quizzzzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // Kiem tra token trong Shared Preferences
        // Neu co token thi chuyen qua Man Hinh Chinh
        String token = mPref.getString("TOKEN", null);
        if(token != null) {
            // Mo activity Man Hinh Chinh
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        }
        btnCallActivityLogin = findViewById(R.id.btn_login);
        btnCallActivityRegster = findViewById(R.id.btn_register);
        txtCallActivityForgotPassowrd = findViewById(R.id.txt_forgot_pass);

        btnCallActivityRegster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        txtCallActivityForgotPassowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgorPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void dangNhap(View view) {
        final EditText mTenDangNhap = findViewById(R.id.txt_username);
        EditText mMatKhau = findViewById(R.id.txt_pass);
        final String ten = mTenDangNhap.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            new FectDangNhap(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        // Lay gia tri cua key "success"
                        boolean success = jsonObject.getBoolean("success");
                        String message = jsonObject.getString("message");
                        if(success) {
                            // Luu token vao Shared Preferences
                            String token = jsonObject.getString("token");

                            SharedPreferences.Editor editor = mPref.edit();
                            editor.putString("TOKEN", token);
                            editor.apply();

                            // Mo activity Man Hinh Chinh
                            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                            intent.putExtra("luuten",ten);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }.execute("nguoi-choi/dang-nhap", "POST", mTenDangNhap.getText().toString(), mMatKhau.getText().toString());

        } else {
            Toast.makeText(this, "Khong the ket noi den server", Toast.LENGTH_SHORT).show();
        }
    }

}
