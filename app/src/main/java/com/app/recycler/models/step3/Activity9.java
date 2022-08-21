
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity9 {

    @SerializedName("activity_9_start_date")
    @Expose
    private String activity9StartDate;
    @SerializedName("activity_9_end_date")
    @Expose
    private String activity9EndDate;
    @SerializedName("activity_9_file")
    @Expose
    private String activity9File;
    @SerializedName("activity_29_file")
    @Expose
    private String activity29File;
    @SerializedName("activity_9_comments_observations")
    @Expose
    private String activity9CommentsObservations;
    @SerializedName("activity_9_curr_status")
    @Expose
    private Activity9CurrStatus activity9CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__1 question;

    public String getActivity9StartDate() {
        return activity9StartDate;
    }

    public void setActivity9StartDate(String activity9StartDate) {
        this.activity9StartDate = activity9StartDate;
    }

    public String getActivity9EndDate() {
        return activity9EndDate;
    }

    public void setActivity9EndDate(String activity9EndDate) {
        this.activity9EndDate = activity9EndDate;
    }

    public String getActivity9File() {
        return activity9File;
    }

    public void setActivity9File(String activity9File) {
        this.activity9File = activity9File;
    }

    public String getActivity29File() {
        return activity29File;
    }

    public void setActivity29File(String activity29File) {
        this.activity29File = activity29File;
    }

    public String getActivity9CommentsObservations() {
        return activity9CommentsObservations;
    }

    public void setActivity9CommentsObservations(String activity9CommentsObservations) {
        this.activity9CommentsObservations = activity9CommentsObservations;
    }

    public Activity9CurrStatus getActivity9CurrStatus() {
        return activity9CurrStatus;
    }

    public void setActivity9CurrStatus(Activity9CurrStatus activity9CurrStatus) {
        this.activity9CurrStatus = activity9CurrStatus;
    }

    public Question__1 getQuestion() {
        return question;
    }

    public void setQuestion(Question__1 question) {
        this.question = question;
    }

}
