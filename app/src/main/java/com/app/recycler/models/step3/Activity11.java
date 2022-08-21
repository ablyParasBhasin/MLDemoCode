
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity11 {

    @SerializedName("activity_11_start_date")
    @Expose
    private String activity11StartDate;
    @SerializedName("activity_11_end_date")
    @Expose
    private String activity11EndDate;
    @SerializedName("activity_11_file")
    @Expose
    private String activity11File;
    @SerializedName("activity_211_file")
    @Expose
    private String activity211File;
    @SerializedName("activity_11_comments_observations")
    @Expose
    private String activity11CommentsObservations;
    @SerializedName("activity_11_curr_status")
    @Expose
    private Activity11CurrStatus activity11CurrStatus;
    @SerializedName("question")
    @Expose
    private Question question;

    public String getActivity11StartDate() {
        return activity11StartDate;
    }

    public void setActivity11StartDate(String activity11StartDate) {
        this.activity11StartDate = activity11StartDate;
    }

    public String getActivity11EndDate() {
        return activity11EndDate;
    }

    public void setActivity11EndDate(String activity11EndDate) {
        this.activity11EndDate = activity11EndDate;
    }

    public String getActivity11File() {
        return activity11File;
    }

    public void setActivity11File(String activity11File) {
        this.activity11File = activity11File;
    }

    public String getActivity211File() {
        return activity211File;
    }

    public void setActivity211File(String activity211File) {
        this.activity211File = activity211File;
    }

    public String getActivity11CommentsObservations() {
        return activity11CommentsObservations;
    }

    public void setActivity11CommentsObservations(String activity11CommentsObservations) {
        this.activity11CommentsObservations = activity11CommentsObservations;
    }

    public Activity11CurrStatus getActivity11CurrStatus() {
        return activity11CurrStatus;
    }

    public void setActivity11CurrStatus(Activity11CurrStatus activity11CurrStatus) {
        this.activity11CurrStatus = activity11CurrStatus;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
