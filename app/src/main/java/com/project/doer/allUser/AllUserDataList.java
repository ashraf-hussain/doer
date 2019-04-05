package com.project.doer.allUser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllUserDataList {
    public List<AllUsersModel> getAllUsersModelList() {
        return allUsersModelList;
    }

    @SerializedName("data")
    private List<AllUsersModel> allUsersModelList;
}
