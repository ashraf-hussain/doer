package com.project.doer.userTask;


import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.project.doer.R;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.common.BaseFragment;
import com.project.doer.common.ConnectionDetector;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.login.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class UserTaskFragment extends BaseFragment implements UserTaskView {

    TaskAdapter taskAdapter;
    @BindView(R.id.rv_user_task)
    RecyclerView rvUserTask;
    ConnectionDetector connectionDetector;
    SharedPreferences sharedPreferences;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    UserTaskPresenter userTaskPresenter;
    @BindView(R.id.user_task_swipe_refresh)
    SwipeRefreshLayout pullToRefresh;
    private static final String TAG = UserTaskFragment.class.getSimpleName();


    @Override
    public void showTaskList(List<TaskModel> taskModelList) {

        taskAdapter = new TaskAdapter(taskModelList);
        rvUserTask.setAdapter(taskAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_task;
    }

    @Override
    protected void init() {
        connectionDetector = new ConnectionDetector(getContext());

        rvUserTask.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvUserTask.setLayoutManager(layoutManager);
        userTaskPresenter = new UserTaskImp(this, this.getActivity());
        userTaskPresenter.loadUserTask(LoginActivity.userGroupId);
        //pull to refresh action
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!connectionDetector.isConnected()) {
                    llNoInternet.setVisibility(View.VISIBLE);
                    AppUtils.showSnackbar(getActivity(), pullToRefresh,
                            getString(R.string.no_internet_connection));
                    pullToRefresh.setRefreshing(false);
                } else {
                    userTaskPresenter.loadUserTask(LoginActivity.userGroupId);
                    llNoInternet.setVisibility(View.GONE);
                    pullToRefresh.setRefreshing(false);
                    AppUtils.showSnackbar(getActivity(), pullToRefresh,
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

            userTaskPresenter.loadUserTask(LoginActivity.userGroupId);

        } else {
            llNoInternet.setVisibility(View.VISIBLE);
            AppUtils.showSnackbar(this.getActivity(), pullToRefresh,
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
}