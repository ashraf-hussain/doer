package com.project.doer.adminTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.group.GroupModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminTaskListActivity extends BaseActivity implements AdminTaskListView {
    @BindView(R.id.rv_admin_task_list)
    RecyclerView rvAdminTaskList;
    GroupModel groupModel;
    @BindView(R.id.tb_title)
    TextView tbTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_admin_task_list;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tbTitle.setText(R.string.all_task);
        rvAdminTaskList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAdminTaskList.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        AdminTaskListPresenter adminTaskListPresenter = new
                AdminTaskListImp(this, this);

        if (intent.getExtras() != null) {
            groupModel = (GroupModel) intent.getSerializableExtra(AppConstants.ASSIGN_TASK);

            adminTaskListPresenter.loadAllCreatedTask(groupModel.getId());
        }

    }

    @OnClick(R.id.fab_create_group)
    public void onViewClicked() {
        Intent i = new Intent(this, AssignTaskActivity.class);
        i.putExtra(AppConstants.ASSIGN_TASK, groupModel);
        startActivity(i);
        finish();
    }

    @Override
    public void showAllTaskCreated(List<TaskModel> taskModelList) {
        AdminTaskAdapter taskAdapter = new AdminTaskAdapter(taskModelList);
        rvAdminTaskList.setAdapter(taskAdapter);
    }

    @OnClick(R.id.toolbar)
    public void onClicked() {
        onBackPressed();
    }
}
