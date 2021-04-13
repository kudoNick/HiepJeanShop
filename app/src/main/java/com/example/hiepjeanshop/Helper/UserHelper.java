package com.example.hiepjeanshop.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hiepjeanshop.Moder.User;
import com.google.gson.Gson;

public class UserHelper {
    private static String PREFERENCE_NAME = "HiepJeanShop";
    private static String USER_DATA = "userData";
    Gson gson = new Gson();
    Context context;

    public UserHelper(Context context) {
        this.context = context;
    }

    public void setUser(User user){
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        String userString = gson.toJson(user);
        preference.edit().putString(USER_DATA, userString).apply();
    }

    public User getUser() {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        User user = new User();
        String userString = preference.getString(USER_DATA, "");
        user = gson.fromJson(userString, User.class);
        return user;
    }
}
