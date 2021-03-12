package com.example.hiepjeanshop.Acitivity.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Adapter.ShoppingAdapter;
import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private RecyclerView rcvShopping;
    private ShoppingAdapter shoppingAdapter;
    private List<Shopping> shoppingList;
    private Shopping shopping;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shopping, container, false);
        rcvShopping = root.findViewById(R.id.rcvShopping);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvShopping.setLayoutManager(layoutManager1);

        shoppingList = new ArrayList<>();
        AndroidNetworking.get("http://192.168.1.5:8080/api/products")
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
                        shoppingAdapter = new ShoppingAdapter(shoppingList, getContext());
                        rcvShopping.setAdapter(shoppingAdapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(), anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        return root;
    }
}