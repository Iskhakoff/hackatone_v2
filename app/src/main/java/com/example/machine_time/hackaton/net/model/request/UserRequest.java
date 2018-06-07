package com.example.machine_time.hackaton.net.model.request;

import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("login")
    private String login;
    @SerializedName("pass")
    private String pass;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
