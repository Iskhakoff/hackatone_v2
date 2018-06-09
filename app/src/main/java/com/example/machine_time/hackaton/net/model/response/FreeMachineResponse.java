package com.example.machine_time.hackaton.net.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FreeMachineResponse {

    @SerializedName("washing_machines")
    private List<WashingMachinesResponse> washingMachines = null;

    public List<WashingMachinesResponse> getWashingMachines() {
        return washingMachines;
    }

    public void setWashingMachines(List<WashingMachinesResponse> washingMachines) {
        this.washingMachines = washingMachines;
    }
}
