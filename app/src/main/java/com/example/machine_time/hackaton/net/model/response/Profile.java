package com.example.machine_time.hackaton.net.model.response;

import com.google.gson.annotations.SerializedName;

public class Profile{

    @SerializedName("fullname")
    private String fullname;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("dormitory")
    private int dormitory;
    @SerializedName("room")
    private String room;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDormitory() {
        return dormitory;
    }

    public void setDormitory(int dormitory) {
        this.dormitory = dormitory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
