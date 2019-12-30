package com.example.quizzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayerActivity extends AppCompatActivity {
    private TextView txtCallActivityPlay;
    private FloatingActionButton btnCallActivityAccountManage;
    private ImageButton btnCallActivityCredit;
    private ImageButton btnLeaderBoard;
    private String sharedPrefFile = "com.example.quizzzzzz";
    private SharedPreferences mPreferences;
    private TextView mThongBao, mToken;
    private TextView txt_credit;
    private  TextView txt_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        txt_credit=findViewById(R.id.textView_credit);
        Intent intent = getIntent();
        String credit = intent.getStringExtra("credit");
        txt_credit.setText(credit);
        txt_username = findViewById(R.id.textView_username);
        final String username = intent.getStringExtra("luuten");
        txt_username.setText(username);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        txtCallActivityPlay = findViewById(R.id.textView_play);
        btnLeaderBoard = (ImageButton)findViewById(R.id.imageButton_leaderboard);
        Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        txtCallActivityPlay.startAnimation(animScale);
        btnCallActivityAccountManage = findViewById(R.id.floatingActionButton_quan_ly_tk);
        btnCallActivityCredit = findViewById(R.id.imageButton_add_credit);
        txtCallActivityPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PlayerActivity.this, TroChoiActivity.class);
                intent.putExtra("luucredit",txt_credit.getText());
                intent.putExtra("tenuser",username);
                startActivity(intent);
            }
        });
        btnCallActivityAccountManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(PlayerActivity.this);
                dialog.setContentView(R.layout.dialog_quan_ly_tai_khoan);
                Button btnDangXuat = dialog.findViewById(R.id.btn_dangxuat);
                Button btnQuanLyTK = dialog.findViewById(R.id.btn_quanlyTK);
                btnDangXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                btnQuanLyTK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PlayerActivity.this, AccountManage.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btnCallActivityCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_credit.getText().toString()==""){
                    Intent intent = new Intent(PlayerActivity.this, CreditActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(PlayerActivity.this, CreditActivity.class);
                    intent.putExtra("luucredit",txt_credit.getText());
                    startActivity(intent);
                }

            }
        });
        btnLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerActivity.this, LeaderBoardActivity.class);
                startActivity(intent);
            }
        });
    }


}
