package com.example.hiepjeanshop.Acitivity.Accout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiepjeanshop.R;

public class SignActivity extends AppCompatActivity {

    public EditText edtFullName,edtAccout,edtPass,edtPassAgain,edtNumber,edtaddress;
    public Button btnSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        thamChieu();

        //button Sign
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void thamChieu(){
        edtFullName = findViewById(R.id.edtFullName);
        edtAccout = findViewById(R.id.edtAccout);
        edtPass = findViewById(R.id.edtPass);
        edtPassAgain = findViewById(R.id.edtPassAgain);
        edtNumber = findViewById(R.id.edtNumber);
        edtaddress = findViewById(R.id.edtaddress);
        btnSign = findViewById(R.id.btnSign);
    }
}