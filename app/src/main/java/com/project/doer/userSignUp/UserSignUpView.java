package com.project.doer.userSignUp;

import com.project.doer.group.GroupModel;

import java.util.List;

public interface UserSignUpView {
    void onSuccess(String msg);
    void onFailure(String msg);
    void userGroupListResponse(List<GroupModel> groupModelList);
}
