package com.project.doer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.doer.common.BaseActivity;
import com.project.doer.user.UserDashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGroupActivity extends BaseActivity {
    @BindView(R.id.et_group_start_date)
    EditText etGroupStartDate;
    @BindView(R.id.spinner_platform_list)
    Spinner spinnerPlatformList;
    @BindView(R.id.et_group_name)
    EditText etGroupName;

    @Override
    protected int getLayout() {
        return R.layout.activity_create_new_group;
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.btn_create_group)
    public void onViewClicked() {
        Intent i = new Intent(CreateGroupActivity.this, UserDashboardActivity.class);
        startActivity(i);
    }
}
