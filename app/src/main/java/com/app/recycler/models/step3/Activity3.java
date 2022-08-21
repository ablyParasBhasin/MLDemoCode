
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity3 {

    @SerializedName("activity_3_start_date")
    @Expose
    private String activity3StartDate;
    @SerializedName("activity_3_end_date")
    @Expose
    private String activity3EndDate;
    @SerializedName("activity_3_file")
    @Expose
    private String activity3File;
    @SerializedName("activity_23_file")
    @Expose
    private String activity23File;
    @SerializedName("activity_3_comments_observations")
    @Expose
    private String activity3CommentsObservations;
    @SerializedName("activity_3_curr_status")
    @Expose
    private Activity3CurrStatus activity3CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__11 question;

    public String getActivity3StartDate() {
        return activity3StartDate;
    }

    public void setActivity3StartDate(String activity3StartDate) {
        this.activity3StartDate = activity3StartDate;
    }

    public String getActivity3EndDate() {
        return activity3EndDate;
    }

    public void setActivity3EndDate(String activity3EndDate) {
        this.activity3EndDate = activity3EndDate;
    }

    public String getActivity3File() {
        return activity3File;
    }

    public void setActivity3File(String activity3File) {
        this.activity3File = activity3File;
    }

    public String getActivity23File() {
        return activity23File;
    }

    public void setActivity23File(String activity23File) {
        this.activity23File = activity23File;
    }

    public String getActivity3CommentsObservations() {
        return activity3CommentsObservations;
    }

    public void setActivity3CommentsObservations(String activity3CommentsObservations) {
        this.activity3CommentsObservations = activity3CommentsObservations;
    }

    public Activity3CurrStatus getActivity3CurrStatus() {
        return activity3CurrStatus;
    }

    public void setActivity3CurrStatus(Activity3CurrStatus activity3CurrStatus) {
        this.activity3CurrStatus = activity3CurrStatus;
    }

    public Question__11 getQuestion() {
        return question;
    }

    public void setQuestion(Question__11 question) {
        this.question = question;
    }

}
