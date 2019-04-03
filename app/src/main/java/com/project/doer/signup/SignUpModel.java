package com.project.doer.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpModel {

    @SerializedName("first_name")

    private String firstName;
    @SerializedName("last_name")

    private String lastName;
    @SerializedName("email")

    private String email;
    @SerializedName("dob")

    private String dob;
    @SerializedName("gender")

    private String gender;
    @SerializedName("avatar")
    private String avatar;

    @SerializedName("password")
    private String password;

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("registration_token")
    private String registrationToken;

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public SignUpModel(String firstName, String lastName, String email, String password, String dob,
                       String gender, String groupId, String registrationToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.groupId = groupId;
        this.registrationToken = registrationToken;
    }

    public SignUpModel(String firstName, String lastName, String email, String password, String dob,
                       String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

}
