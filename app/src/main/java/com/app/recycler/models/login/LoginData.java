package com.app.recycler.models.login;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    private String id;
      @SerializedName("email")
    private String email;
      @SerializedName("role_id")
    private String role_id;
      @SerializedName("role_name")
    private String role_name;
      @SerializedName("name")
    private String name;
      @SerializedName("login_token")
    private String login_token;
      @SerializedName("profile_pic")
    private String profile_pic;
    @SerializedName("login_token_expire_datetime")
    private String login_token_expire_datetime;
      
    public String serialize() {
        // Serialize this class into a JSON string using GSON
        Gson gson= new Gson();
        return gson.toJson(this);
    }
    public static LoginData create(String serializedData) {
        // Use GSON to instantiate this class using the JSON representation of the state
        Gson gson= new Gson();
        return gson.fromJson(serializedData, LoginData.class);
    }
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getName() {
        return name;
    }

    public String getLogin_token() {
        return login_token;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getLogin_token_expire_datetime() {
        return login_token_expire_datetime;
    }

    public void setLogin_token_expire_datetime(String login_token_expire_datetime) {
        this.login_token_expire_datetime = login_token_expire_datetime;
    }
}