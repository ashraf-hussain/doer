package com.project.doer.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.adminNotice.GetAllNoticeActivity;
import com.project.doer.common.BaseActivity;
import com.project.doer.group.GetAllGroupsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminLandingActivity extends BaseActivity {
    @BindView(R.id.tb_title)
    TextView tbTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_admin_landing;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tbTitle.setText(R.string.dashbaord);
    }

    @OnClick({R.id.cv_event, R.id.cv_groups})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_event:
                Intent eventIntent = new Intent(this, GetAllNoticeActivity.class);
                startActivity(eventIntent);
                break;
            case R.id.cv_groups:
                Intent taskIntent = new Intent(this, GetAllGroupsActivity.class);
                startActivity(taskIntent);
                break;
        }
    }

    @OnClick(R.id.toolbar)
    public void onViewClicked() {
        onBackPressed();
    }
}
