
package com.app.recycler.models.step3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName(value = "activity_1_start_date", alternate = {"activity_2_start_date","activity_3_start_date","activity_4_start_date","activity_5_start_date","activity_6_start_date","activity_7_start_date","activity_8_start_date","activity_9_start_date","activity_10_start_date","activity_11_start_date","activity_12_start_date","activity_13_start_date","activity_14_start_date","activity_15_start_date","activity_16_start_date","activity_17_start_date"})
    @Expose
    private String activityStartDate;
    @SerializedName(value ="activity_1_end_date", alternate = {"activity_2_end_date","activity_3_end_date","activity_4_end_date","activity_5_end_date","activity_6_end_date","activity_7_end_date","activity_8_end_date","activity_9_end_date","activity_10_end_date","activity_11_end_date","activity_12_end_date","activity_13_end_date","activity_14_end_date","activity_15_end_date","activity_16_end_date","activity_17_end_date"})
    @Expose
    private String activityEndDate;
    @SerializedName(value = "activity_1_file", alternate = {"activity_2_file","activity_3_file","activity_4_file","activity_5_file","activity_6_file","activity_7_file","activity_8_file","activity_9_file","activity_10_file","activity_11_file","activity_12_file","activity_13_file","activity_14_file","activity_15_file","activity_16_file","activity_17_file"})
    @Expose
    private String activityFileFirst;
    @SerializedName(value = "activity_21_file", alternate = {"activity_211_file","activity_212_file","activity_213_file","activity_23_file","activity_24_file","activity_25_file","activity_29_file","activity_28_file","activity_27_file","activity_210_file","activity_26_file","activity_22_file"})
    @Expose
    private String activityFileSecond;
    @SerializedName(value = "activity_1_comments_observations", alternate = {"activity_2_comments_observations","activity_3_comments_observations","activity_4_comments_observations","activity_5_comments_observations","activity_6_comments_observations","activity_7_comments_observations","activity_8_comments_observations","activity_9_comments_observations","activity_10_comments_observations","activity_11_comments_observations","activity_12_comments_observations","activity_13_comments_observations","activity_14_comments_observations","activity_15_comments_observations","activity_16_comments_observations","activity_17_comments_observations"})
    @Expose
    private String activityCommentsObservations;
    @SerializedName(value = "activity_1_curr_status", alternate = {"activity_2_curr_status","activity_3_curr_status","activity_4_curr_status","activity_5_curr_status","activity_6_curr_status","activity_7_curr_status","activity_8_curr_status","activity_9_curr_status","activity_10_curr_status","activity_11_curr_status","activity_12_curr_status","activity_13_curr_status","activity_14_curr_status","activity_15_curr_status","activity_16_curr_status","activity_17_curr_status"})
    @Expose
    private ActivityCurrStatus activityCurrStatus;
    @SerializedName("question")
    @Expose
    private Question question;

    public String getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getActivityFileFirst() {
        return activityFileFirst;
    }

    public void setActivityFileFirst(String activityFileFirst) {
        this.activityFileFirst = activityFileFirst;
    }

    public String getActivityFileSecond() {
        return activityFileSecond;
    }

    public void setActivityFileSecond(String activityFileSecond) {
        this.activityFileSecond = activityFileSecond;
    }

    public String getActivityCommentsObservations() {
        return activityCommentsObservations;
    }

    public void setActivityCommentsObservations(String activityCommentsObservations) {
        this.activityCommentsObservations = activityCommentsObservations;
    }

    public ActivityCurrStatus getActivityCurrStatus() {
        return activityCurrStatus;
    }

    public void setActivityCurrStatus(ActivityCurrStatus activityCurrStatus) {
        this.activityCurrStatus = activityCurrStatus;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
