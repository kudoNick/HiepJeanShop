package com.example.hiepjeanshop.Acitivity.Accout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Acitivity.MainActivity;
import com.example.hiepjeanshop.Helper.UserHelper;
import com.example.hiepjeanshop.Moder.User;
import com.example.hiepjeanshop.R;
import com.example.hiepjeanshop.api.APIUrls;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtAccout,edtPass;
    private Button btnlogin;
    private TextView tvSign;
    UserHelper userHelper;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        thamChieu();




    }
    public void thamChieu() {
        edtAccout = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        btnlogin = findViewById(R.id.btnLogIn);
        tvSign = findViewById(R.id.tvSign);
    }

    public void logIn(View view) {
        String userName = edtAccout.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.loading);
        dialog.show();

        AndroidNetworking.post(APIUrls.URL_GET_LOGIN)
                .addBodyParameter("userName", userName)
                .addBodyParameter("password", password)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            if (status == 400){
                                Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                            }else {
                                user = new User(response.getJSONObject("result"));
                                userHelper = new UserHelper(LoginActivity.this);
                                userHelper.setUser(user);
                                dialog.dismiss();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(LoginActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void signUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignActivity.class);
        startActivity(intent);
    }
}