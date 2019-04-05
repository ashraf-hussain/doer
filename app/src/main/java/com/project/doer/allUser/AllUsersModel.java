package com.project.doer.allUser;

import com.google.gson.annotations.SerializedName;
import com.project.doer.login.RoleModel;

import java.io.Serializable;

public class AllUsersModel implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String firstName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SerializedName("last_name")
    private String lastName;

    public RoleModel getRoleModel() {
        return roleModel;
    }

    @SerializedName("role")
    private RoleModel roleModel;

}
