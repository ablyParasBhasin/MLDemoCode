
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity4 {

    @SerializedName("activity_4_start_date")
    @Expose
    private String activity4StartDate;
    @SerializedName("activity_4_end_date")
    @Expose
    private String activity4EndDate;
    @SerializedName("activity_4_file")
    @Expose
    private String activity4File;
    @SerializedName("activity_24_file")
    @Expose
    private String activity24File;
    @SerializedName("activity_4_comments_observations")
    @Expose
    private String activity4CommentsObservations;
    @SerializedName("activity_4_curr_status")
    @Expose
    private Activity4CurrStatus activity4CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__8 question;

    public String getActivity4StartDate() {
        return activity4StartDate;
    }

    public void setActivity4StartDate(String activity4StartDate) {
        this.activity4StartDate = activity4StartDate;
    }

    public String getActivity4EndDate() {
        return activity4EndDate;
    }

    public void setActivity4EndDate(String activity4EndDate) {
        this.activity4EndDate = activity4EndDate;
    }

    public String getActivity4File() {
        return activity4File;
    }

    public void setActivity4File(String activity4File) {
        this.activity4File = activity4File;
    }

    public String getActivity24File() {
        return activity24File;
    }

    public void setActivity24File(String activity24File) {
        this.activity24File = activity24File;
    }

    public String getActivity4CommentsObservations() {
        return activity4CommentsObservations;
    }

    public void setActivity4CommentsObservations(String activity4CommentsObservations) {
        this.activity4CommentsObservations = activity4CommentsObservations;
    }

    public Activity4CurrStatus getActivity4CurrStatus() {
        return activity4CurrStatus;
    }

    public void setActivity4CurrStatus(Activity4CurrStatus activity4CurrStatus) {
        this.activity4CurrStatus = activity4CurrStatus;
    }

    public Question__8 getQuestion() {
        return question;
    }

    public void setQuestion(Question__8 question) {
        this.question = question;
    }

}
