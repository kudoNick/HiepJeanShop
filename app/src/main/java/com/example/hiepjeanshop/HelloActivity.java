package com.example.hiepjeanshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiepjeanshop.Acitivity.MainActivity;

public class HelloActivity extends AppCompatActivity {

    private TextView tvSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        tvSkip = findViewById(R.id.tvSkip);

        CountDownTimer Timer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvSkip.setText("Bỏ qua: " + millisUntilFinished/1000);
            }

            public void onFinish() {
                Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
    }

}