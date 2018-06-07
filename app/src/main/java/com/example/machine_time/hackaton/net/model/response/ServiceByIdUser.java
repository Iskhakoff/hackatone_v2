package com.example.machine_time.hackaton.net.model.response;

import android.support.annotation.Nullable;

import com.example.machine_time.hackaton.net.model.request.Requests;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceByIdUser {
    @Nullable
    @SerializedName("requests")
    private List<Requests> requests = null;

    @Nullable
    public List<Requests> getRequests() {
        return requests;
    }

    public void setRequests(@Nullable List<Requests> requests) {
        this.requests = requests;
    }

    public ServiceByIdUser(List<Requests> requests) {
        this.requests = requests;
    }
}
