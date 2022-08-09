package com.app.recycler.models.step1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonData {

@SerializedName("id")
@Expose
private String id;

    @SerializedName("activity_id")
    @Expose
    private String activity_id;

    public String getActivity_id() {
        return activity_id;
    }

    public String getForm_start_date_time() {
        return form_start_date_time;
    }

    @SerializedName("form_start_date_time")
    @Expose
    private String form_start_date_time;

@SerializedName("estate_name")
@Expose
private String estateName;
@SerializedName("estate_district_name")
@Expose
private String estate_district_name;

    public String getEstate_district_name() {
        return estate_district_name;
    }

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getEstateName() {
return estateName;
}

public void setEstateName(String estateName) {
this.estateName = estateName;
}

}