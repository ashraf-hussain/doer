package com.project.doer.userNotice;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseFragment;
import com.project.doer.common.ConnectionDetector;
import com.project.doer.data.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserNoticeFragment extends BaseFragment implements UserNoticeView {

    NoticeAdapter noticeAdapter;

    UserNoticePresenter userNoticePresenter;
    ConnectionDetector connectionDetector;

    private static final String TAG = UserNoticeFragment.class.getSimpleName();
    @BindView(R.id.tv_offline_mode)
    TextView tvOfflineMode;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    @BindView(R.id.rv_user_notice)
    RecyclerView rvUserNotice;
    @BindView(R.id.user_notice_swipe_refresh)
    SwipeRefreshLayout userNoticeSwipeRefresh;


    @Override
    public void showUserNoticeList(List<EventModel> eventModelList) {

        noticeAdapter = new NoticeAdapter(eventModelList);
        rvUserNotice.setAdapter(noticeAdapter);
    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(getActivity(), msg);
    }

    @Override
    public void onError(String msg) {
        AppUtils.showToast(getActivity(), msg);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_notice;
    }

    @Override
    protected void init() {
        connectionDetector = new ConnectionDetector(getContext());

        rvUserNotice.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvUserNotice.setLayoutManager(layoutManager);
        userNoticePresenter = new UserNoticeImp(this.getActivity(), this);
        userNoticePresenter.loadUserEventNotice();
        //pull to refresh action
        userNoticeSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!connectionDetector.isConnected()) {
                    llNoInternet.setVisibility(View.VISIBLE);
                    AppUtils.showSnackbar(getActivity(), userNoticeSwipeRefresh,
                            getString(R.string.no_internet_connection));
                    userNoticeSwipeRefresh.setRefreshing(false);
                } else {
                    userNoticePresenter.loadUserEventNotice();
                    llNoInternet.setVisibility(View.GONE);
                    userNoticeSwipeRefresh.setRefreshing(false);
                    AppUtils.showSnackbar(getActivity(), userNoticeSwipeRefresh,
                            getString(R.string.data_loaded));

                }
            }
        });

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
            AppUtils.showSnackbar(this.getActivity(), userNoticeSwipeRefresh,
                    getString(R.string.no_internet_connection));
        }
    }


    @OnClick({R.id.btn_retry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                checkpoint();
                break;
        }
    }


    @OnClick(R.id.btn_retry)
    public void onViewClicked() {
    }
}