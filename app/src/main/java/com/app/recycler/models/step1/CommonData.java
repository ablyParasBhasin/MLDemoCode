package com.app.recycler.models.step1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CommonData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("msg")
    @Expose
    private String msg="";


   /* "user_id": "1",
            "activity_id": "765024",
            "status": "In Progress",
            "form_start_date_time": "2022-08-19 12:06:16",
            "form_end_date_time": null,
            "user_name": "Digant Gupta",
            "email_id": "digantgupta@nirwana.in",
            "estate_name": "Desam",
            "estate_district_name": "Dibrugarh"*/

    @SerializedName("user_id")
    @Expose
    String user_id;


    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("form_start_date_time")
    @Expose
    String form_start_date_time;


    @SerializedName("form_end_date_time")
    @Expose
    String form_end_date_time;

    @SerializedName("user_name")
    @Expose
    String user_name;

    @SerializedName("email_id")
    @Expose
    String email_id;

    @SerializedName("estate_name")
    @Expose
    String estate_name;

    @SerializedName("estate_district_name")
    @Expose
    String estate_district_name;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForm_end_date_time() {
        return form_end_date_time;
    }

    public void setForm_end_date_time(String form_end_date_time) {
        this.form_end_date_time = form_end_date_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getEstate_name() {
        return estate_name;
    }

    public void setEstate_name(String estate_name) {
        this.estate_name = estate_name;
    }

    public String getMsg() {
        return msg;
    }
    private LinkedHashMap<String,String> arrayListActivitiesMap;

    public LinkedHashMap<String, String> getArrayListActivitiesMap() {
        return arrayListActivitiesMap;
    }

    public void setArrayListActivitiesMap(LinkedHashMap<String, String> arrayListActivitiesMap) {
        this.arrayListActivitiesMap = arrayListActivitiesMap;
    }
   private ArrayList<String> arrayListActivities;

    public ArrayList<String> getArrayListActivities() {
        return arrayListActivities;
    }

    public void setArrayListActivities(ArrayList<String> arrayListActivities) {
        this.arrayListActivities = arrayListActivities;
    }

    public CommonData(String activityName, ArrayList<String> str) {
        this.categoryName=activityName;
        this.arrayListActivities=str;
    }
    public CommonData(String activityName, LinkedHashMap<String, String> arrayListActivitiesMap) {
        this.categoryName=activityName;
        this.arrayListActivitiesMap=arrayListActivitiesMap;
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
     String categoryName;

    @SerializedName("activity_id")
    @Expose
    private String activity_id;

    public String getActivity_id() {
        return activity_id;
    }

    public String getForm_start_date_time() {
        return form_start_date_time;
    }



    @SerializedName("estate_name")
    @Expose
    private String estateName;


    public String getEstate_district_name() {
        return estate_district_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public void setForm_start_date_time(String form_start_date_time) {
        this.form_start_date_time = form_start_date_time;
    }

    public void setEstate_district_name(String estate_district_name) {
        this.estate_district_name = estate_district_name;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

}