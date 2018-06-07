package com.example.machine_time.hackaton.net.model.request;

import com.google.gson.annotations.SerializedName;

public class ServiceByIdRequest {

    @SerializedName("user_id")
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
