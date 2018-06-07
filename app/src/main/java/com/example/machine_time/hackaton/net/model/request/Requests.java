package com.example.machine_time.hackaton.net.model.request;

import com.google.gson.annotations.SerializedName;

public class Requests {

    @SerializedName("id")
    private String id;
    @SerializedName("created_at")
    private String created_ad;
    @SerializedName("employee_id")
    private String employee_id;
    @SerializedName("problem_description")
    private String problem_description;
    @SerializedName("desired_time")
    private String desired_time;
    @SerializedName("status")
    private String status;
    @SerializedName("user_id")
    private String user_id;

    public Requests(String id, String created_ad, String employee_id, String problem_description, String desired_time, String status, String user_id) {
        this.id = id;
        this.created_ad = created_ad;
        this.employee_id = employee_id;
        this.problem_description = problem_description;
        this.desired_time = desired_time;
        this.status = status;
        this.user_id = user_id;
    }

    public String getId()   {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_ad() {
        return created_ad;
    }

    public void setCreated_ad(String created_ad) {
        this.created_ad = created_ad;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getProblem_description() {
        return problem_description;
    }

    public void setProblem_description(String problem_description) {
        this.problem_description = problem_description;
    }

    public String getDesired_time() {
        return desired_time;
    }

    public void setDesired_time(String desired_time) {
        this.desired_time = desired_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
