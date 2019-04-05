package com.project.doer.login;

import com.google.gson.annotations.SerializedName;

public class LoginModelBody {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;


}
