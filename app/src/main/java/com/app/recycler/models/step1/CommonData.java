package com.app.recycler.models.step1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonData {

@SerializedName("id")
@Expose
private String id;
@SerializedName("estate_name")
@Expose
private String estateName;

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