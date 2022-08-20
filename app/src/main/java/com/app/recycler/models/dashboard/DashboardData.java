package com.app.recycler.models.dashboard;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class DashboardData {
    @SerializedName("In Progress")
    private String inProgress="0";
    @SerializedName("Rejected")
    private String rejected="0";
    @SerializedName("Approved")
    private String approved="0";
    @SerializedName("Completed")
    private String completed="0";

    public String getInProgress() {
        return inProgress;
    }

    public String getRejected() {
        return rejected;
    }

    public String getApproved() {
        return approved;
    }

    public String getCompleted() {
        return completed;
    }

    public String getRejectForModification() {
        return rejectForModification;
    }

    @SerializedName("Reject For Modification")
    private String rejectForModification="0";
}