package com.app.recycler.interfaces;

import com.app.recycler.apinetworks.API_TAG;

import retrofit2.Response;


public interface ResponseHandler {
    void onSuccess(API_TAG tag, Response response);

    void onFailure(API_TAG tag, Throwable t);
}
