package com.example.hiepjeanshop.Acitivity.Cart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Helper.UserHelper;
import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.Moder.User;
import com.example.hiepjeanshop.R;
import com.example.hiepjeanshop.api.APIUrls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    String id,amout;
    RecyclerView rcvCard;
    CartAdapter cardAdapter;
    Shopping shopping;
    List<Shopping> shoppingList = new ArrayList<>();

    private User user;
    private UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        thamChieu();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();
        amout = intent.getStringExtra("amout");

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.loading);
        dialog.show();
        userHelper = new UserHelper(this);
        user = new User();
        user = userHelper.getUser();
        AndroidNetworking.post(APIUrls.URL_GET_MYCARTS)
                .addBodyParameter("userName",user.getUserName())
                .addBodyParameter("password",user.getPassword())
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        shopping = new Shopping(jsonArray.getJSONObject(i));
                        shoppingList.add(shopping);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cardAdapter = new CartAdapter(shoppingList,CartActivity.this,amout);
                rcvCard.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                rcvCard.setAdapter(cardAdapter);
                dialog.dismiss();
            }
            @Override
            public void onError(ANError anError) {
                Toast.makeText(CartActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void thamChieu(){
        rcvCard = findViewById(R.id.rcvCard);
    }
}