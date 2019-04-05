package com.project.doer.allUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllUserActivity extends BaseActivity implements AllUserView {
    @BindView(R.id.rv_all_user_list)
    RecyclerView rvAllUsers;
    AllUserPresenter allUserPresenter;
    TaskModel taskModel;
    public static int taskId;
    @BindView(R.id.tb_title)
    TextView tbTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_all_user_list;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tbTitle.setText(R.string.all_developers);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            taskModel = (TaskModel) intent.getSerializableExtra(AppConstants.TASK_DETAIL);
            taskId = Integer.parseInt(taskModel.getId());

        }

        rvAllUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAllUsers.setLayoutManager(layoutManager);

        allUserPresenter = new AllUserImp(this, this);
        allUserPresenter.loadAllUsers();

    }

    @Override
    public void showAllUsers(List<AllUsersModel> allUsersModels) {
        AllUserAdapter allGroupAdapter = new AllUserAdapter(allUsersModels);
        rvAllUsers.setAdapter(allGroupAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        allUserPresenter.loadAllUsers();
    }


    @OnClick(R.id.toolbar)
    public void onViewClicked() {
        onBackPressed();
    }
}
