package com.project.doer.adminTask;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.group.GroupModel;

import butterknife.BindView;
import butterknife.OnClick;

public class AssignTaskActivity extends BaseActivity implements AssignTaskView {
    @BindView(R.id.et_assign_task_title)
    EditText etAssignTaskTitle;
    @BindView(R.id.et_assign_task_todo_description)
    EditText etAssignTaskTodoDescription;
    @BindView(R.id.et_assign_task_todo_deadline)
    EditText etAssignTaskTodoDeadline;
    @BindView(R.id.btn_assign_task_upload_task)
    Button btnAssignTaskUploadTask;
    @BindView(R.id.sv_assign_task)
    ScrollView svLogin;
    String title, description, deadline;
    GroupModel groupModel;
    AssignTaskPresenter assignTaskPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_assign_task;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            groupModel = (GroupModel) intent.getSerializableExtra(AppConstants.ASSIGN_TASK);
        }

        assignTaskPresenter = new AssignTaskImp(this, this);

    }

    @OnClick({R.id.et_assign_task_todo_deadline, R.id.btn_assign_task_upload_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_assign_task_todo_deadline:
                break;
            case R.id.btn_assign_task_upload_task:
                processLogin(groupModel.getId());
                break;
        }
    }

    private void processLogin(int groupId) {
        title = etAssignTaskTitle.getText().toString();
        description = etAssignTaskTodoDescription.getText().toString();
        deadline = etAssignTaskTodoDeadline.getText().toString();
        if (title.isEmpty()) {
            etAssignTaskTitle.setError("Required!");

        } else if (description.isEmpty()) {
            etAssignTaskTodoDescription.setError("Required!");

        } else if (deadline.isEmpty()) {
            etAssignTaskTodoDeadline.setError("Required!");
        }
        if (!title.equalsIgnoreCase("")
                && !description.equalsIgnoreCase("")
                && !deadline.equalsIgnoreCase("")) {
            TaskModel taskModel = new TaskModel(title, description, deadline, groupId);
            assignTaskPresenter.uploadTask(taskModel);


        }
    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(this, msg);
        Intent i = new Intent(this, AdminTaskListActivity.class);
        i.putExtra(AppConstants.ASSIGN_TASK, groupModel);
        startActivity(i);
        finish();
    }

    @Override
    public void onError(String msg) {
        AppUtils.showToast(this, msg);

    }
}
