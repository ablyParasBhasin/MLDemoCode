package com.app.recycler.apinetworks;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    @SerializedName("msg")
    private String msg;

    @SerializedName("status")
    private String status;

    public int getCartItemCount() {
        return cartItemCount;
    }

    @SerializedName("cartItemCount")
    private int cartItemCount=0;

    public int getFavoriteItemCount() {
        return favoriteItemCount;
    }

    @SerializedName("favoriteItemCount")
    private int favoriteItemCount=0;

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
