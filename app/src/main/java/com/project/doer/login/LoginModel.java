package com.project.doer.login;

import com.google.gson.annotations.SerializedName;
import com.project.doer.group.GroupModel;

public class LoginModel {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    @SerializedName("token")
    private String token;


    public GroupModel getGroupModel() {
        return groupModel;
    }

    @SerializedName("group")
    private GroupModel groupModel;

    @SerializedName("role")
    private RoleModel roleModel;

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }


    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
