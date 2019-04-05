package com.project.doer.adminTask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;

import com.project.doer.R;
import com.project.doer.adminNotice.AdminCreateNoticeActivity;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.group.GroupModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignTaskActivity extends BaseActivity implements AssignTaskView {
    @BindView(R.id.et_assign_task_title)
    EditText etAssignTaskTitle;
    @BindView(R.id.et_assign_task_todo_description)
    EditText etAssignTaskTodoDescription;
    @BindView(R.id.et_assign_task_todo_deadline)
    EditText etAssignTaskTodoDeadline;
    @BindView(R.id.sv_assign_task)
    ScrollView svLogin;
    String title, description, deadline, eventTime;
    GroupModel groupModel;
    AssignTaskPresenter assignTaskPresenter;
    @BindView(R.id.et_assign_task_todo_time)
    EditText etAssignTaskTodoTime;
    private Calendar calendar;
    private int mHour, mMinute;
    private DatePickerDialog.OnDateSetListener date;

    @Override
    protected int getLayout() {
        return R.layout.activity_assign_task;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        calendar = Calendar.getInstance();
        datePickerAction();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            groupModel = (GroupModel) intent.getSerializableExtra(AppConstants.ASSIGN_TASK);
        }

        assignTaskPresenter = new AssignTaskImp(this, this);

    }

    //TimePicker
    private void timePickerAction() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String time;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            time = hourOfDay + ":" + minute + " AM";
                        } else {
                            if (hourOfDay == 12) {
                                time = hourOfDay + ":" + minute + " PM";
                            } else {
                                hourOfDay = hourOfDay - 12;
                                time = hourOfDay + ":" + minute + " PM";
                            }
                        }

                        etAssignTaskTodoTime.setText(time);
                        eventTime = time;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    //DatePicker
    private void datePickerAction() {
        //DatePicker
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

            private void updateDate() {
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                etAssignTaskTodoDeadline.setText(simpleDateFormat.format(calendar.getTime()));
                deadline = simpleDateFormat.format(calendar.getTime());
            }
        };
    }

    @OnClick({R.id.et_assign_task_todo_deadline, R.id.btn_assign_task_upload_task, R.id.et_assign_task_todo_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_assign_task_todo_deadline:
                new DatePickerDialog(AssignTaskActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_assign_task_upload_task:
                processLogin(groupModel.getId());
                break;
            case R.id.et_assign_task_todo_time:
                timePickerAction();
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
        } else if (eventTime.isEmpty()) {
            etAssignTaskTodoTime.setError("Required!");
        }
        if (!title.equalsIgnoreCase("")
                && !description.equalsIgnoreCase("")
                && !deadline.equalsIgnoreCase("")
                && !eventTime.equalsIgnoreCase("")) {
            TaskModel taskModel = new TaskModel(title, description, deadline, groupId, eventTime, true);
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


    @OnClick(R.id.back_button)
    public void onViewClicked() {
        onBackPressed();
    }
}
