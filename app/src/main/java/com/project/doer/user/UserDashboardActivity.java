package com.project.doer.user;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.userNotice.UserNoticeFragment;
import com.project.doer.userTask.UserTaskFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDashboardActivity extends BaseActivity {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tb_title)
    TextView tbTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_tab_layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tbTitle.setText(R.string.dashbaord);
        UserTabAdapter adapter = new UserTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserTaskFragment(), "Task");
        adapter.addFragment(new UserNoticeFragment(), "Notice");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private int[] tabIcons = {
            R.drawable.ic_format_list_bulleted_black_24dp,
            R.drawable.ic_notifications_none_black_24dp
    };
}
