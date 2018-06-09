package com.example.machine_time.hackaton.net.model.response;

import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("id")
    private int id;
    @SerializedName("time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
