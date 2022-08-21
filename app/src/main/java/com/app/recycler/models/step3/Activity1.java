
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity1 {

    @SerializedName("activity_1_start_date")
    @Expose
    private String activity1StartDate;
    @SerializedName("activity_1_end_date")
    @Expose
    private String activity1EndDate;
    @SerializedName("activity_1_file")
    @Expose
    private String activity1File;
    @SerializedName("activity_21_file")
    @Expose
    private String activity21File;
    @SerializedName("activity_1_comments_observations")
    @Expose
    private String activity1CommentsObservations;
    @SerializedName("activity_1_curr_status")
    @Expose
    private Activity1CurrStatus activity1CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__5 question;

    public String getActivity1StartDate() {
        return activity1StartDate;
    }

    public void setActivity1StartDate(String activity1StartDate) {
        this.activity1StartDate = activity1StartDate;
    }

    public String getActivity1EndDate() {
        return activity1EndDate;
    }

    public void setActivity1EndDate(String activity1EndDate) {
        this.activity1EndDate = activity1EndDate;
    }

    public String getActivity1File() {
        return activity1File;
    }

    public void setActivity1File(String activity1File) {
        this.activity1File = activity1File;
    }

    public String getActivity21File() {
        return activity21File;
    }

    public void setActivity21File(String activity21File) {
        this.activity21File = activity21File;
    }

    public String getActivity1CommentsObservations() {
        return activity1CommentsObservations;
    }

    public void setActivity1CommentsObservations(String activity1CommentsObservations) {
        this.activity1CommentsObservations = activity1CommentsObservations;
    }

    public Activity1CurrStatus getActivity1CurrStatus() {
        return activity1CurrStatus;
    }

    public void setActivity1CurrStatus(Activity1CurrStatus activity1CurrStatus) {
        this.activity1CurrStatus = activity1CurrStatus;
    }

    public Question__5 getQuestion() {
        return question;
    }

    public void setQuestion(Question__5 question) {
        this.question = question;
    }

}
