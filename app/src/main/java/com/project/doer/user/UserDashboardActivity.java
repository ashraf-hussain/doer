package com.project.doer.user;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;


import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.userNotice.UserNoticeFragment;
import com.project.doer.userReview.UserReviewFragment;
import com.project.doer.userTask.UserTaskFragment;

import butterknife.BindView;

public class UserDashboardActivity extends BaseActivity {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_tab_layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        UserTabAdapter adapter = new UserTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserTaskFragment(), "Task");
        adapter.addFragment(new UserReviewFragment(), "Review");
        adapter.addFragment(new UserNoticeFragment(), "Notice");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private int[] tabIcons = {
            R.drawable.ic_format_list_bulleted_black_24dp,
            R.drawable.ic_rate_review_black_24dp,
            R.drawable.ic_notifications_none_black_24dp
    };

}
