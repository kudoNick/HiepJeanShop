package com.example.hiepjeanshop.Moder;

import org.json.JSONException;
import org.json.JSONObject;

public class Shopping {
    public String name,price,image;


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
