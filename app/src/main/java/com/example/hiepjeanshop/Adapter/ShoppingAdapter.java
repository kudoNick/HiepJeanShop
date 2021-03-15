package com.example.hiepjeanshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiepjeanshop.Acitivity.ChildProductActivity;
import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingPlaceHolder> {

    List<Shopping> shoppingList;
    Context context;

    public ShoppingAdapter(List<Shopping> shoppingList, Context context) {
        this.shoppingList = shoppingList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping,parent,false);
        ShoppingPlaceHolder shoppingPlaceHolder = new ShoppingPlaceHolder(view);

        return shoppingPlaceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingAdapter.ShoppingPlaceHolder holder, int position) {

        final Shopping shopping = shoppingList.get(position);
        Picasso.get().load(shopping.getImage()).into(holder.imgProduct);
        holder.tvName.setText(shopping.getName());

        Locale localeVN = new Locale("", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt(shopping.getPrice()));
        holder.tvPrice.setText(str1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChildProductActivity.class);
                intent.putExtra("name", shopping.getName());
                intent.putExtra("price", shopping.getPrice());
                intent.putExtra("image", shopping.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class ShoppingPlaceHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice;

        public ShoppingPlaceHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
