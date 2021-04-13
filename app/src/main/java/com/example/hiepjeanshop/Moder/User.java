package com.example.hiepjeanshop.Moder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    String id,userName,fullName,password,address,phoneNumber,avatar;
    int role;
    ArrayList<String> cart;


    public User() {
    }

    public User(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("userName")) {
            userName = jsonObject.getString("userName");
        }
        if (jsonObject.has("password")) {
            password = jsonObject.getString("password");
        }
    }

    public User(String userName, String fullName, String password, String address, String phoneNumber, String avatar, int role, ArrayList<String> cart) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.role = role;
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getRole() {
        return role;
    }

    public ArrayList<String> getCart() {
        return cart;
    }
}
