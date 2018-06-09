package com.example.machine_time.hackaton.net.model.response;

import com.google.gson.annotations.SerializedName;

public class WashingScheduleResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("date")
    private String date;
    @SerializedName("washing_machine")
    private int washingMachine;
    @SerializedName("user")
    private int user;
    @SerializedName("time")
    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(int washingMachine) {
        this.washingMachine = washingMachine;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
