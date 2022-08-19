package com.app.recycler.models.step1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("msg")
    @Expose
    private String msg="";

    public String getMsg() {
        return msg;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

   private boolean expandable=false;

    public boolean isCatChecked() {
        return isCatChecked;
    }

    public void setCatChecked(boolean catChecked) {
        isCatChecked = catChecked;
    }

    private boolean isCatChecked =false;
    private boolean isSubCatChecked =false;

    public boolean isSubCatChecked() {
        return isSubCatChecked;
    }

    public void setSubCatChecked(boolean subCatChecked) {
        isSubCatChecked = subCatChecked;
    }

    public String getActivityName() {
        return activityName;
    }

    @SerializedName("activity_name")
    @Expose
    private String activityName;

    public String getCategoryName() {
        return categoryName;
    }

    @SerializedName("category_name")
    @Expose
    private String categoryName;

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