package com.example.machine_time.hackaton.net.model.request;

import com.google.gson.annotations.SerializedName;

public class WashingScheduleRequest {

    @SerializedName("date")
    private String date;
    @SerializedName("washing_machine")
    private String washingMachine;
    @SerializedName("user")
    private String user;
    @SerializedName("time")
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(String washingMachine) {
        this.washingMachine = washingMachine;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
