package com.app.recycler.models;

import java.util.List;

import com.app.recycler.models.step1.CommonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponseArray<T> {
    @SerializedName("error_message")
    private String error_message;
@SerializedName("code")
@Expose
private String status;
@SerializedName("data")
@Expose
private List<CommonData> data = null;

public String getStatus() {
return status;
}

public void setStatus(Integer code) {
this.status = status;
}

public List<CommonData> getData() {
return data;
}

public void setData(List<CommonData> data) {
this.data = data;
}
    public String getMsg() {
        return error_message;
    }
}