package com.project.doer.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.user.UserDashboardActivity;
import com.project.doer.userSignUp.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements BaseView {
    @BindView(R.id.et_login_email)
    EditText etLoginEmail;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    String email, password;
    LoginPresenter loginPresenter;
    SharedPreferences sharedPreferences;
    @BindView(R.id.sv_login)
    ScrollView svLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AppUtils.showSnackbar(this, svLogin, "Working");

        loginPresenter = new LoginImp(this);
    }

    @OnClick({R.id.btn_signIn, R.id.tv_crete_ac})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                processLogin();
                break;
            case R.id.tv_crete_ac:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showSnackbar(this, svLogin, msg);
        save(msg);
        Intent i = new Intent(this, UserDashboardActivity.class);
        startActivity(i);
    }

    @Override
    public void onFailure(String msg) {
        AppUtils.showSnackbar(this, svLogin, msg);

    }

    private void processLogin() {
        email = etLoginEmail.getText().toString();
        password = etLoginPassword.getText().toString();
        if (email.isEmpty()) {
            etLoginEmail.setError("Required!");

        } else if (password.isEmpty()) {
            etLoginPassword.setError("Required!");

        }

        if (!email.equalsIgnoreCase("")
                && !password.equalsIgnoreCase("")) {
            LoginModel loginModel = new LoginModel(email, password);
            loginPresenter.sendLoginData(loginModel);
        }
    }

    void save(String value) {
        sharedPreferences = getSharedPreferences(AppConstants.TOKEN_DATA,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.TOKEN, value);
        editor.commit();



    }

}
