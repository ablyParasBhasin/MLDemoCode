
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity8CurrStatus {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("options")
    @Expose
    private String options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

}
