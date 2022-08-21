
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity2 {

    @SerializedName("activity_2_start_date")
    @Expose
    private String activity2StartDate;
    @SerializedName("activity_2_end_date")
    @Expose
    private String activity2EndDate;
    @SerializedName("activity_2_file")
    @Expose
    private String activity2File;
    @SerializedName("activity_22_file")
    @Expose
    private String activity22File;
    @SerializedName("activity_2_comments_observations")
    @Expose
    private String activity2CommentsObservations;
    @SerializedName("activity_2_curr_status")
    @Expose
    private Activity2CurrStatus activity2CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__9 question;

    public String getActivity2StartDate() {
        return activity2StartDate;
    }

    public void setActivity2StartDate(String activity2StartDate) {
        this.activity2StartDate = activity2StartDate;
    }

    public String getActivity2EndDate() {
        return activity2EndDate;
    }

    public void setActivity2EndDate(String activity2EndDate) {
        this.activity2EndDate = activity2EndDate;
    }

    public String getActivity2File() {
        return activity2File;
    }

    public void setActivity2File(String activity2File) {
        this.activity2File = activity2File;
    }

    public String getActivity22File() {
        return activity22File;
    }

    public void setActivity22File(String activity22File) {
        this.activity22File = activity22File;
    }

    public String getActivity2CommentsObservations() {
        return activity2CommentsObservations;
    }

    public void setActivity2CommentsObservations(String activity2CommentsObservations) {
        this.activity2CommentsObservations = activity2CommentsObservations;
    }

    public Activity2CurrStatus getActivity2CurrStatus() {
        return activity2CurrStatus;
    }

    public void setActivity2CurrStatus(Activity2CurrStatus activity2CurrStatus) {
        this.activity2CurrStatus = activity2CurrStatus;
    }

    public Question__9 getQuestion() {
        return question;
    }

    public void setQuestion(Question__9 question) {
        this.question = question;
    }

}
