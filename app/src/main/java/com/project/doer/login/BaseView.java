package com.project.doer.login;

public interface BaseView {
    void onSuccess(String token, String role, int groupId);
    void onFailure(String msg);
}
