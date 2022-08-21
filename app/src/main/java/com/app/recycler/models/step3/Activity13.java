
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity13 {

    @SerializedName("activity_13_start_date")
    @Expose
    private String activity13StartDate;
    @SerializedName("activity_13_end_date")
    @Expose
    private String activity13EndDate;
    @SerializedName("activity_13_file")
    @Expose
    private String activity13File;
    @SerializedName("activity_213_file")
    @Expose
    private String activity213File;
    @SerializedName("activity_13_comments_observations")
    @Expose
    private String activity13CommentsObservations;
    @SerializedName("activity_13_curr_status")
    @Expose
    private Activity13CurrStatus activity13CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__3 question;

    public String getActivity13StartDate() {
        return activity13StartDate;
    }

    public void setActivity13StartDate(String activity13StartDate) {
        this.activity13StartDate = activity13StartDate;
    }

    public String getActivity13EndDate() {
        return activity13EndDate;
    }

    public void setActivity13EndDate(String activity13EndDate) {
        this.activity13EndDate = activity13EndDate;
    }

    public String getActivity13File() {
        return activity13File;
    }

    public void setActivity13File(String activity13File) {
        this.activity13File = activity13File;
    }

    public String getActivity213File() {
        return activity213File;
    }

    public void setActivity213File(String activity213File) {
        this.activity213File = activity213File;
    }

    public String getActivity13CommentsObservations() {
        return activity13CommentsObservations;
    }

    public void setActivity13CommentsObservations(String activity13CommentsObservations) {
        this.activity13CommentsObservations = activity13CommentsObservations;
    }

    public Activity13CurrStatus getActivity13CurrStatus() {
        return activity13CurrStatus;
    }

    public void setActivity13CurrStatus(Activity13CurrStatus activity13CurrStatus) {
        this.activity13CurrStatus = activity13CurrStatus;
    }

    public Question__3 getQuestion() {
        return question;
    }

    public void setQuestion(Question__3 question) {
        this.question = question;
    }

}
