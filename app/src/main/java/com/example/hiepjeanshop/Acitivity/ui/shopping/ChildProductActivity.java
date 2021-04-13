package com.example.hiepjeanshop.Acitivity.ui.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.hiepjeanshop.api.APIUrls;
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

    private ProgressBar proBar;

    Toolbar toolbar;
    private ImageView imgProduct;
    private TextView tvPrice,tvName;
    private Button btnBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_product);


        //hiện thị thêm sản phẩm
        rcvShopping = findViewById(R.id.rcvShopping);
        rcvShopping.setHasFixedSize(true);
        rcvShopping.setLayoutManager(new GridLayoutManager(this, 2));
        shoppingList = new ArrayList<>();
        String url = APIUrls.URL_GET_PRODUCTS;
        AndroidNetworking.get(url)
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
                        proBar.setVisibility(View.GONE);
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
        String amount = intent.getStringExtra("amount");
        String id = intent.getStringExtra("id");

        Picasso.get().load(image).into(imgProduct);

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

        //button buy
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyProductFragment buyProductFragment = new BuyProductFragment(id,name,image,price,amount,ChildProductActivity.this);
                buyProductFragment.show(getSupportFragmentManager(),"Mua san pham");
            }
        });
    }
    private void thamChieu(){
        imgProduct = findViewById(R.id.imgProduct);
        tvPrice = findViewById(R.id.tvPrice);
        tvName = findViewById(R.id.tvName);
        btnBuy = findViewById(R.id.btnBuy);
        proBar = findViewById(R.id.proBar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}