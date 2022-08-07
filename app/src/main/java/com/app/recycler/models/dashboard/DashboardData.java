package com.app.recycler.models.dashboard;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class DashboardData {
    @SerializedName("In Progress")
    private String inProgress;
    @SerializedName("Rejected")
    private String rejected;
    @SerializedName("Approved")
    private String approved;
    @SerializedName("Completed")
    private String completed;

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
    private String rejectForModification;
}