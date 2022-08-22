
package com.app.recycler.models.step3;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityCurrStatus {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("options")
    @Expose
    private ArrayList<String> options = null;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

}
