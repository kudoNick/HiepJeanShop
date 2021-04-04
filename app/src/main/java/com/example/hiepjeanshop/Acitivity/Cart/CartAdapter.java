package com.example.hiepjeanshop.Acitivity.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CardviewHolder> {
    TextView tvName,tvPrice,tvSize,tvAmount;
    ImageView imgImage;
    List<Shopping> productBuys;
    Context context;
    String amout;
    public CartAdapter(List<Shopping> productBuy, Context context,String amout) {
        this.productBuys = productBuy;
        this.context = context;
        this.amout = amout;
    }

    @NonNull
    @Override
    public CardviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart, null);
        CardviewHolder cardviewHolder = new CardviewHolder(view);
        return cardviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewHolder holder, int position) {
        Shopping shopping = productBuys.get(position);

        tvName.setText(shopping.getName());
        tvPrice.setText(shopping.getPrice());
        tvAmount.setText(amout);
        tvSize.setText("XL");
        Picasso.get().load(shopping.getImage()).into(imgImage);

    }

    @Override
    public int getItemCount() {
        return productBuys.size();
    }

    public class CardviewHolder extends RecyclerView.ViewHolder {
        public CardviewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgImage = itemView.findViewById(R.id.imgProduct);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
