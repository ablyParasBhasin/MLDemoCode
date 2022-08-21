
package com.app.recycler.models.step3;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Activity10 {

    @SerializedName("activity_10_start_date")
    @Expose
    private String activity10StartDate;
    @SerializedName("activity_10_end_date")
    @Expose
    private String activity10EndDate;
    @SerializedName("activity_10_file")
    @Expose
    private String activity10File;
    @SerializedName("activity_210_file")
    @Expose
    private String activity210File;
    @SerializedName("activity_10_comments_observations")
    @Expose
    private String activity10CommentsObservations;
    @SerializedName("activity_10_curr_status")
    @Expose
    private Activity10CurrStatus activity10CurrStatus;
    @SerializedName("question")
    @Expose
    private Question__6 question;

    public String getActivity10StartDate() {
        return activity10StartDate;
    }

    public void setActivity10StartDate(String activity10StartDate) {
        this.activity10StartDate = activity10StartDate;
    }

    public String getActivity10EndDate() {
        return activity10EndDate;
    }

    public void setActivity10EndDate(String activity10EndDate) {
        this.activity10EndDate = activity10EndDate;
    }

    public String getActivity10File() {
        return activity10File;
    }

    public void setActivity10File(String activity10File) {
        this.activity10File = activity10File;
    }

    public String getActivity210File() {
        return activity210File;
    }

    public void setActivity210File(String activity210File) {
        this.activity210File = activity210File;
    }

    public String getActivity10CommentsObservations() {
        return activity10CommentsObservations;
    }

    public void setActivity10CommentsObservations(String activity10CommentsObservations) {
        this.activity10CommentsObservations = activity10CommentsObservations;
    }

    public Activity10CurrStatus getActivity10CurrStatus() {
        return activity10CurrStatus;
    }

    public void setActivity10CurrStatus(Activity10CurrStatus activity10CurrStatus) {
        this.activity10CurrStatus = activity10CurrStatus;
    }

    public Question__6 getQuestion() {
        return question;
    }

    public void setQuestion(Question__6 question) {
        this.question = question;
    }

}
