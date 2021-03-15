package com.example.hiepjeanshop.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Adapter.ShoppingAdapter;
import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChildProductActivity extends AppCompatActivity {

    private RecyclerView rcvShopping;
    private ShoppingAdapter shoppingAdapter;
    private List<Shopping> shoppingList;
    private Shopping shopping;

    Toolbar toolbar;
    private ImageView imgProduct;
    private TextView tvPrice,tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_product);


        //hiện thị thêm sản phẩm
        rcvShopping = findViewById(R.id.rcvShopping);
        rcvShopping.setHasFixedSize(true);
        rcvShopping.setLayoutManager(new GridLayoutManager(this, 2));
        shoppingList = new ArrayList<>();
        AndroidNetworking.get("http://192.168.0.128:8080/api/products")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray products = response.getJSONArray("products");
                            for (int i = 0; i < products.length(); i++) {
                                shopping = new Shopping(products.getJSONObject(i));
                                shoppingList.add(shopping);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        shoppingAdapter = new ShoppingAdapter(shoppingList, ChildProductActivity.this);
                        rcvShopping.setAdapter(shoppingAdapter);

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ChildProductActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


        thamChieu();
        // Get Data
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String image = intent.getStringExtra("image");

        Picasso.get().load(image).into(imgProduct);
        tvPrice.setText(price);
        Locale localeVN = new Locale("", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt((price)));
        tvPrice.setText(str1);
        tvName.setText(name);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    private void thamChieu(){
        imgProduct = findViewById(R.id.imgProduct);
        tvPrice = findViewById(R.id.tvPrice);
        tvName = findViewById(R.id.tvName);



    }

}