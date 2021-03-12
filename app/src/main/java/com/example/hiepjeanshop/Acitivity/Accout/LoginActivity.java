package com.example.hiepjeanshop.Acitivity.Accout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiepjeanshop.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtAccout,edtPass;
    Button btnlogin;
    TextView tvSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtAccout = findViewById(R.id.edtAccout);
        edtPass = findViewById(R.id.edtPass);
        btnlogin = findViewById(R.id.btnLogIn);
        tvSign = findViewById(R.id.tvSign);

        //Button login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //button Sign
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }
}