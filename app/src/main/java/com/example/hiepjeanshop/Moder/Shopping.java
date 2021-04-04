package com.example.hiepjeanshop.Moder;

import org.json.JSONException;
import org.json.JSONObject;

public class Shopping {
    public String name,price,image,amount,id;


    public Shopping(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Shopping(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("name")) {
            name = jsonObject.getString("name");
        }
        if (jsonObject.has("price")) {
            price = jsonObject.getString("price");
        }
        if (jsonObject.has("productThumb")) {
            image = jsonObject.getString("productThumb");
        }
        if (jsonObject.has("amount")) {
            amount = jsonObject.getString("amount");
        }if (jsonObject.has("_id")){
            id = jsonObject.getString("_id");
        }
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
