package com.example.hiepjeanshop.Acitivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hiepjeanshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class BuyProductFragment extends BottomSheetDialogFragment {
    private ImageView imgProduct;
    private TextView tvPrice,tvAmount,tvName;
    private Button btnBuy;
    private String image,price,amount,name;
    Context context;
    public BuyProductFragment(String name,String image, String price, String amount,Context context) {
        this.image = image;
        this.price = price;
        this.amount = amount;
        this.context = context;
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.buy_product, container, false);
        imgProduct = view.findViewById(R.id.imgProduct);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvAmount = view.findViewById(R.id.tvAmount);
        btnBuy = view.findViewById(R.id.btnBuy);

        Picasso.get().load(image).into(imgProduct);
        tvAmount.setText("kho:" + amount);

        Locale localeVN = new Locale("", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt((price)));
        tvPrice.setText(str1);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.confirm_product);
                imgProduct = dialog.findViewById(R.id.imgProduct);
                tvPrice = dialog.findViewById(R.id.tvPrice);
                tvName = dialog.findViewById(R.id.tvName);

                Picasso.get().load(image).into(imgProduct);
                Locale localeVN = new Locale("", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(Integer.parseInt((price)));
                tvPrice.setText(str1);
                tvName.setText(name);


                dialog.show();

            }
        });
        return view;
    }
}
