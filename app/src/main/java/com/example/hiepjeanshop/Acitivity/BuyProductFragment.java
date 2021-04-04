package com.example.hiepjeanshop.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Acitivity.Cart.CartActivity;
import com.example.hiepjeanshop.R;
import com.example.hiepjeanshop.api.APIUrls;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

public class BuyProductFragment extends BottomSheetDialogFragment {
    private ImageView imgProduct;
    private TextView tvPrice,tvAmount,tvName,tvSize,tvTotalPrice;
    private Button btnBuy;
    private String image,price,amount,name,id,amountTo;
    private EditText edtAmount;
    private RadioButton radio_S,radio_M,radio_L,radio_XL;
    private RadioGroup radioGr;
    private int isChecked;
    Context context;
    public BuyProductFragment(String id ,String name,String image, String price, String amount,Context context) {
        this.image = image;
        this.price = price;
        this.amount = amount;
        this.context = context;
        this.name = name;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.buy_product, container, false);
        imgProduct = view.findViewById(R.id.imgProduct);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvAmount = view.findViewById(R.id.tvAmount);
        btnBuy = view.findViewById(R.id.btnBuy);
        edtAmount = view.findViewById(R.id.edtAmount);
        radio_S = view.findViewById(R.id.radio_S);
        radio_M = view.findViewById(R.id.radio_M);
        radio_L = view.findViewById(R.id.radio_L);
        radio_XL = view.findViewById(R.id.radio_XL);
        radioGr = view.findViewById(R.id.radioGr);



        Picasso.get().load(image).into(imgProduct);
        tvAmount.setText("kho:" + amount);

        Locale localeVN = new Locale("", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt((price)));
        tvPrice.setText(str1);


        radioGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnBuy.setEnabled(true);
                edtAmount.setEnabled(true);
                edtAmount.setTextColor(Color.BLUE);
            }
        });


        //đặt con trỏ ở cuối
        edtAmount.setSelection(edtAmount.getText().length());
        //giới hạn số lượng bằng trong kho
        int maxLength = amount.length();
        edtAmount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    btnBuy.setEnabled(false);
                }else {
                    btnBuy.setEnabled(true);
                }
                try {
                    int a = Integer.parseInt(s.toString());
                    int b = Integer.parseInt(amount);
                    if (a > b) {
                        btnBuy.setEnabled(false);
                    }else {
                        btnBuy.setEnabled(true);
                    }
                }catch (Exception e){
                }
            }
        });
        amountTo = edtAmount.getText().toString().trim();
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(APIUrls.URL_GET_ADDCARTS)
                .addBodyParameter("userName","tuanichi06")
                .addBodyParameter("password","061200")
                .addBodyParameter("id",id)
                .setPriority(Priority.LOW)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("products");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                Shopping shopping = new Shopping(jsonArray.getJSONObject(i));
//                                CartActivity.shoppingList.add(shopping);
//                            }
//                        } catch (JSONException e) {
//                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
//                        }

                        Intent intent = new Intent(getContext(), CartActivity.class);
                        intent.putExtra("amout", amountTo);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

//                Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.confirm_product);
//                imgProduct = dialog.findViewById(R.id.imgProduct);
//                tvPrice = dialog.findViewById(R.id.tvPrice);
//                tvName = dialog.findViewById(R.id.tvName);
//                tvAmount = dialog.findViewById(R.id.tvAmount);
//                tvSize = dialog.findViewById(R.id.tvSize);
//                tvTotalPrice = dialog.findViewById(R.id.tvTotalPrice);
//
//
//                isChecked = radioGr.getCheckedRadioButtonId();
//                switch (isChecked){
//                    case R.id.radio_S:
//                        tvSize.setText("Size: S");
//                        break;
//                    case R.id.radio_M:
//                        tvSize.setText("Size: M");
//                        break;
//                    case R.id.radio_L:
//                        tvSize.setText("Size: L");
//                        break;
//                    case R.id.radio_XL:
//                        tvSize.setText("Size: XL");
//                        break;
//                }
//
//                Picasso.get().load(image).into(imgProduct);
//                Locale localeVN = new Locale("", "VN");
//                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
//                String price = currencyVN.format(Integer.parseInt((BuyProductFragment.this.price)));
//                tvPrice.setText(price);
//                tvName.setText(name);
//                String soLuong = edtAmount.getText().toString();
//                tvAmount.setText("Số lượng: " + soLuong);
//
//                //tổng giá
//
//                int totalPrice = 0;
//                totalPrice = Integer.parseInt(soLuong) * Integer.parseInt((BuyProductFragment.this.price));
//                String totalPriceText = currencyVN.format(totalPrice);
//                tvTotalPrice.setText("Tổng tiền: " + totalPriceText);
//
//                dialog.show();
            }
        });
        return view;
    }
}
