package com.example.machine_time.hackaton.net.model.request;

import com.google.gson.annotations.SerializedName;

public class ServiceRequest {

    @SerializedName("type")
    private String type;
    @SerializedName("info")
    private String info;
    @SerializedName("date")
    private String date;
    @SerializedName("user_id")
    private int user_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
