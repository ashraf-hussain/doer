package com.project.doer.userSignUp;

import com.project.doer.group.GroupModel;
import com.project.doer.signup.SignUpModel;

import java.util.List;

public interface UserSignUpPresenter {
    void sendSignUpData(SignUpModel signUpModel);
    void loadGroupData();
}
