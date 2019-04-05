package com.project.doer.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoleModel implements Serializable {

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @SerializedName("role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("id")
    private int id;


}
