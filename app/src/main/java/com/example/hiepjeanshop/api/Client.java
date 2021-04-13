package com.example.hiepjeanshop.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Moder.Shopping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Client {
    public static List<Shopping> shoppingList;
    public static void addCart(List<Shopping> shoppingList){
        AndroidNetworking.get(APIUrls.URL_GET_PRODUCTS)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray products = response.getJSONArray("products");
                            for (int i = 0; i < products.length(); i++) {
                               Shopping shopping = new Shopping(products.getJSONObject(i));
                                shoppingList.add(shopping);
                                System.out.println("tuan anh");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        shoppingAdapter = new ShoppingAdapter(shoppingList, getContext());
//                        rcvShopping.setAdapter(shoppingAdapter);
                    }

                    @Override
                    public void onError(ANError anError) {
//                        Toast.makeText(getContext(), anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
