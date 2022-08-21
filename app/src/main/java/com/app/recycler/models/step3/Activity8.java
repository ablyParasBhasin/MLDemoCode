
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity8 {

    @SerializedName("activity_8_start_date")
    @Expose
    private String activity8StartDate;
    @SerializedName("activity_8_end_date")
    @Expose
    private String activity8EndDate;
    @SerializedName("activity_8_file")
    @Expose
    private String activity8File;
    @SerializedName("activity_28_file")
    @Expose
    private String activity28File;
    @SerializedName("activity_8_comments_observations")
    @Expose
    private String activity8CommentsObservations;
    @SerializedName("activity_8_curr_status")
    @Expose
    private Activity8CurrStatus activity8CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__12 question;

    public String getActivity8StartDate() {
        return activity8StartDate;
    }

    public void setActivity8StartDate(String activity8StartDate) {
        this.activity8StartDate = activity8StartDate;
    }

    public String getActivity8EndDate() {
        return activity8EndDate;
    }

    public void setActivity8EndDate(String activity8EndDate) {
        this.activity8EndDate = activity8EndDate;
    }

    public String getActivity8File() {
        return activity8File;
    }

    public void setActivity8File(String activity8File) {
        this.activity8File = activity8File;
    }

    public String getActivity28File() {
        return activity28File;
    }

    public void setActivity28File(String activity28File) {
        this.activity28File = activity28File;
    }

    public String getActivity8CommentsObservations() {
        return activity8CommentsObservations;
    }

    public void setActivity8CommentsObservations(String activity8CommentsObservations) {
        this.activity8CommentsObservations = activity8CommentsObservations;
    }

    public Activity8CurrStatus getActivity8CurrStatus() {
        return activity8CurrStatus;
    }

    public void setActivity8CurrStatus(Activity8CurrStatus activity8CurrStatus) {
        this.activity8CurrStatus = activity8CurrStatus;
    }

    public Question__12 getQuestion() {
        return question;
    }

    public void setQuestion(Question__12 question) {
        this.question = question;
    }

}
