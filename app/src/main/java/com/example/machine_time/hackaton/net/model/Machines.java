package com.example.machine_time.hackaton.net.model;

import com.google.gson.annotations.SerializedName;

public class Machines {

    private int id;
    private int number;

    public Machines(int id, int number) {
        this.id = id;
        this.number = number;
    }

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
