package com.example.hiepjeanshop.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Moder.Shopping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static List<Shopping> shoppingList;
    public static void addCart(){
        System.out.println("tuan anh");
        AndroidNetworking.post(APIUrls.URL_GET_ADDCARTS)
                .addBodyParameter("userName", "admin")
                .addBodyParameter("password", "123456")
                .addBodyParameter("id", "604baac91bf8982d141eccd3")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Shopping shopping = new Shopping(jsonArray.getJSONObject(i));
                                 shoppingList = new ArrayList<>();
                                shoppingList.add(shopping);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
