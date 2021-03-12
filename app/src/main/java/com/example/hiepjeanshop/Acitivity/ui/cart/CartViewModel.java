package com.example.hiepjeanshop.Acitivity.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Vui lòng đăng nhập để tiếp tục");
    }

    public LiveData<String> getText() {
        return mText;
    }
}