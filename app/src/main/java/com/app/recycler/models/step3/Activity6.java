
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity6 {

    @SerializedName("activity_6_start_date")
    @Expose
    private String activity6StartDate;
    @SerializedName("activity_6_end_date")
    @Expose
    private String activity6EndDate;
    @SerializedName("activity_6_file")
    @Expose
    private String activity6File;
    @SerializedName("activity_26_file")
    @Expose
    private String activity26File;
    @SerializedName("activity_6_comments_observations")
    @Expose
    private String activity6CommentsObservations;
    @SerializedName("activity_6_curr_status")
    @Expose
    private Activity6CurrStatus activity6CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__4 question;

    public String getActivity6StartDate() {
        return activity6StartDate;
    }

    public void setActivity6StartDate(String activity6StartDate) {
        this.activity6StartDate = activity6StartDate;
    }

    public String getActivity6EndDate() {
        return activity6EndDate;
    }

    public void setActivity6EndDate(String activity6EndDate) {
        this.activity6EndDate = activity6EndDate;
    }

    public String getActivity6File() {
        return activity6File;
    }

    public void setActivity6File(String activity6File) {
        this.activity6File = activity6File;
    }

    public String getActivity26File() {
        return activity26File;
    }

    public void setActivity26File(String activity26File) {
        this.activity26File = activity26File;
    }

    public String getActivity6CommentsObservations() {
        return activity6CommentsObservations;
    }

    public void setActivity6CommentsObservations(String activity6CommentsObservations) {
        this.activity6CommentsObservations = activity6CommentsObservations;
    }

    public Activity6CurrStatus getActivity6CurrStatus() {
        return activity6CurrStatus;
    }

    public void setActivity6CurrStatus(Activity6CurrStatus activity6CurrStatus) {
        this.activity6CurrStatus = activity6CurrStatus;
    }

    public Question__4 getQuestion() {
        return question;
    }

    public void setQuestion(Question__4 question) {
        this.question = question;
    }

}
