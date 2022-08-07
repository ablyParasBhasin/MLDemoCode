package com.app.recycler.models;

import com.app.recycler.models.dashboard.DashboardData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    @SerializedName("error_message")
    private String error_message;

    @SerializedName("code")
    private String status;

    public String getError_message() {
        return error_message;
    }


    public T getData() {
        return data;
    }

    @SerializedName("data")
    private T data=null;

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
        return error_message;
    }

    public String getStatus() {
        return status;
    }
}

