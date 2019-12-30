package com.example.quizzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreditActivity extends AppCompatActivity {
    Button btnCredit;
    Button btnCredit_1000, btnCredit_3000, btnCredit_5000;
    TextView tongCredit;
    int dem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        btnCredit = findViewById(R.id.button_500);
        btnCredit_1000=findViewById(R.id.button_1000);
        btnCredit_3000=findViewById(R.id.button_3000);
        btnCredit_5000=findViewById(R.id.button_5000);
        tongCredit = findViewById(R.id.textView_credit3);

        Intent intent = getIntent();
        tongCredit.setText(intent.getStringExtra("luucredit"));
        Toast.makeText(this,tongCredit.getText().toString(),Toast.LENGTH_SHORT).show();
        if(btnCredit.isClickable()){
            TangCredit(btnCredit);
        }
        if(btnCredit_1000.isClickable()){
            TangCredit(btnCredit_1000);
        }
        if(btnCredit_3000.isClickable()){
            TangCredit(btnCredit_3000);
        }
        if(btnCredit_5000.isClickable()){
            TangCredit(btnCredit_5000);
        }

    }
    private void TangCredit(final Button btnCreditchung){
        btnCreditchung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tongCredit.getText().toString()=="")
                {
                    int tongcredit = 0 + Integer.parseInt(btnCreditchung.getText().toString());
                    tongCredit.setText(Integer.toString(tongcredit));
                }
                else
                {
                    int tongcredit = Integer.parseInt(tongCredit.getText().toString()) + Integer.parseInt(btnCreditchung.getText().toString());
                    tongCredit.setText(Integer.toString(tongcredit));
                }

            }
        });
    }

    public void luuGiaTri(View view) {
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra("credit",tongCredit.getText());
        startActivity(intent);
    }
}
