package com.example.machine_time.hackaton.net.model.response;

import com.google.gson.annotations.SerializedName;

public class WashingMachinesResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("number")
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
