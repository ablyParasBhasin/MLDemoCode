
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity7 {

    @SerializedName("activity_7_start_date")
    @Expose
    private String activity7StartDate;
    @SerializedName("activity_7_end_date")
    @Expose
    private String activity7EndDate;
    @SerializedName("activity_7_file")
    @Expose
    private String activity7File;
    @SerializedName("activity_27_file")
    @Expose
    private String activity27File;
    @SerializedName("activity_7_comments_observations")
    @Expose
    private String activity7CommentsObservations;
    @SerializedName("activity_7_curr_status")
    @Expose
    private Activity7CurrStatus activity7CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__7 question;

    public String getActivity7StartDate() {
        return activity7StartDate;
    }

    public void setActivity7StartDate(String activity7StartDate) {
        this.activity7StartDate = activity7StartDate;
    }

    public String getActivity7EndDate() {
        return activity7EndDate;
    }

    public void setActivity7EndDate(String activity7EndDate) {
        this.activity7EndDate = activity7EndDate;
    }

    public String getActivity7File() {
        return activity7File;
    }

    public void setActivity7File(String activity7File) {
        this.activity7File = activity7File;
    }

    public String getActivity27File() {
        return activity27File;
    }

    public void setActivity27File(String activity27File) {
        this.activity27File = activity27File;
    }

    public String getActivity7CommentsObservations() {
        return activity7CommentsObservations;
    }

    public void setActivity7CommentsObservations(String activity7CommentsObservations) {
        this.activity7CommentsObservations = activity7CommentsObservations;
    }

    public Activity7CurrStatus getActivity7CurrStatus() {
        return activity7CurrStatus;
    }

    public void setActivity7CurrStatus(Activity7CurrStatus activity7CurrStatus) {
        this.activity7CurrStatus = activity7CurrStatus;
    }

    public Question__7 getQuestion() {
        return question;
    }

    public void setQuestion(Question__7 question) {
        this.question = question;
    }

}
