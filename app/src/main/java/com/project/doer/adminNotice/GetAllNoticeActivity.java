package com.project.doer.adminNotice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.common.ConnectionDetector;
import com.project.doer.data.AppUtils;
import com.project.doer.userNotice.EventModel;
import com.project.doer.userNotice.NoticeAdapter;
import com.project.doer.userNotice.UserNoticeFragment;
import com.project.doer.userNotice.UserNoticeImp;
import com.project.doer.userNotice.UserNoticePresenter;
import com.project.doer.userNotice.UserNoticeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetAllNoticeActivity extends BaseActivity implements UserNoticeView {
    NoticeAdapter noticeAdapter;

    UserNoticePresenter userNoticePresenter;
    ConnectionDetector connectionDetector;

    private static final String TAG = UserNoticeFragment.class.getSimpleName();
    @BindView(R.id.tv_offline_mode)
    TextView tvOfflineMode;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    @BindView(R.id.rv_admin_notice)
    RecyclerView rvUserNotice;
    @BindView(R.id.admin_notice_swipe_refresh)
    SwipeRefreshLayout adminNoticeSwipeRefresh;
    @BindView(R.id.tb_title)
    TextView tbTitle;


    @OnClick(R.id.toolbar)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        connectionDetector = new ConnectionDetector(this);
        tbTitle.setText(R.string.all_events);
        rvUserNotice.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserNotice.setLayoutManager(layoutManager);
        userNoticePresenter = new UserNoticeImp(GetAllNoticeActivity.this, this);
        userNoticePresenter.loadUserEventNotice();
        //pull to refresh action
        adminNoticeSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!connectionDetector.isConnected()) {
                    llNoInternet.setVisibility(View.VISIBLE);
                    AppUtils.showSnackbar(GetAllNoticeActivity.this, adminNoticeSwipeRefresh,
                            getString(R.string.no_internet_connection));
                    adminNoticeSwipeRefresh.setRefreshing(false);
                } else {
                    userNoticePresenter.loadUserEventNotice();
                    llNoInternet.setVisibility(View.GONE);
                    adminNoticeSwipeRefresh.setRefreshing(false);
                    AppUtils.showSnackbar(GetAllNoticeActivity.this, adminNoticeSwipeRefresh,
                            getString(R.string.data_loaded));

                }
            }
        });

    }


    @Override
    public void showUserNoticeList(List<EventModel> eventModelList) {

        noticeAdapter = new NoticeAdapter(eventModelList);
        rvUserNotice.setAdapter(noticeAdapter);
    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(this, msg);
    }

    @Override
    public void onError(String msg) {
        AppUtils.showToast(this, msg);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_admin_all_notice;
    }


    @Override
    public void onResume() {
        super.onResume();
        checkpoint();
    }


    @Override
    public void checkpoint() {
        if (connectionDetector.isConnected()) {
            llNoInternet.setVisibility(View.GONE);

            userNoticePresenter.loadUserEventNotice();

        } else {
            llNoInternet.setVisibility(View.VISIBLE);
            AppUtils.showSnackbar(this, adminNoticeSwipeRefresh,
                    getString(R.string.no_internet_connection));
        }
    }


    @OnClick({R.id.btn_retry, R.id.fab_create_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                checkpoint();
                break;
            case R.id.fab_create_event:
                Intent eventIntent = new Intent(this, AdminCreateNoticeActivity.class);
                startActivity(eventIntent);
                break;
        }
    }

    @OnClick(R.id.toolbar)
    public void onClicked() {
        onBackPressed();
    }
}
