
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity5 {

    @SerializedName("activity_5_start_date")
    @Expose
    private String activity5StartDate;
    @SerializedName("activity_5_end_date")
    @Expose
    private String activity5EndDate;
    @SerializedName("activity_5_file")
    @Expose
    private String activity5File;
    @SerializedName("activity_25_file")
    @Expose
    private String activity25File;
    @SerializedName("activity_5_comments_observations")
    @Expose
    private String activity5CommentsObservations;
    @SerializedName("activity_5_curr_status")
    @Expose
    private Activity5CurrStatus activity5CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__10 question;

    public String getActivity5StartDate() {
        return activity5StartDate;
    }

    public void setActivity5StartDate(String activity5StartDate) {
        this.activity5StartDate = activity5StartDate;
    }

    public String getActivity5EndDate() {
        return activity5EndDate;
    }

    public void setActivity5EndDate(String activity5EndDate) {
        this.activity5EndDate = activity5EndDate;
    }

    public String getActivity5File() {
        return activity5File;
    }

    public void setActivity5File(String activity5File) {
        this.activity5File = activity5File;
    }

    public String getActivity25File() {
        return activity25File;
    }

    public void setActivity25File(String activity25File) {
        this.activity25File = activity25File;
    }

    public String getActivity5CommentsObservations() {
        return activity5CommentsObservations;
    }

    public void setActivity5CommentsObservations(String activity5CommentsObservations) {
        this.activity5CommentsObservations = activity5CommentsObservations;
    }

    public Activity5CurrStatus getActivity5CurrStatus() {
        return activity5CurrStatus;
    }

    public void setActivity5CurrStatus(Activity5CurrStatus activity5CurrStatus) {
        this.activity5CurrStatus = activity5CurrStatus;
    }

    public Question__10 getQuestion() {
        return question;
    }

    public void setQuestion(Question__10 question) {
        this.question = question;
    }

}
