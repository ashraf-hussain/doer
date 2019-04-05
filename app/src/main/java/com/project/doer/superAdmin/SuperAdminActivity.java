package com.project.doer.superAdmin;

import android.content.Intent;
import android.os.Bundle;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.login.AdminSignUpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuperAdminActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_super_admin;
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.fab_add_admin)
    public void onViewClicked() {
        Intent i = new Intent(this, AdminSignUpActivity.class);
        startActivity(i);

    }
}
