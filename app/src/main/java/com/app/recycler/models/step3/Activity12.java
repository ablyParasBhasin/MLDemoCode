
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity12 {

    @SerializedName("activity_12_start_date")
    @Expose
    private String activity12StartDate;
    @SerializedName("activity_12_end_date")
    @Expose
    private String activity12EndDate;
    @SerializedName("activity_12_file")
    @Expose
    private String activity12File;
    @SerializedName("activity_212_file")
    @Expose
    private String activity212File;
    @SerializedName("activity_12_comments_observations")
    @Expose
    private String activity12CommentsObservations;
    @SerializedName("activity_12_curr_status")
    @Expose
    private Activity12CurrStatus activity12CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__2 question;

    public String getActivity12StartDate() {
        return activity12StartDate;
    }

    public void setActivity12StartDate(String activity12StartDate) {
        this.activity12StartDate = activity12StartDate;
    }

    public String getActivity12EndDate() {
        return activity12EndDate;
    }

    public void setActivity12EndDate(String activity12EndDate) {
        this.activity12EndDate = activity12EndDate;
    }

    public String getActivity12File() {
        return activity12File;
    }

    public void setActivity12File(String activity12File) {
        this.activity12File = activity12File;
    }

    public String getActivity212File() {
        return activity212File;
    }

    public void setActivity212File(String activity212File) {
        this.activity212File = activity212File;
    }

    public String getActivity12CommentsObservations() {
        return activity12CommentsObservations;
    }

    public void setActivity12CommentsObservations(String activity12CommentsObservations) {
        this.activity12CommentsObservations = activity12CommentsObservations;
    }

    public Activity12CurrStatus getActivity12CurrStatus() {
        return activity12CurrStatus;
    }

    public void setActivity12CurrStatus(Activity12CurrStatus activity12CurrStatus) {
        this.activity12CurrStatus = activity12CurrStatus;
    }

    public Question__2 getQuestion() {
        return question;
    }

    public void setQuestion(Question__2 question) {
        this.question = question;
    }

}
