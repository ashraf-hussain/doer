package com.project.doer.userTask;

import com.project.doer.adminTask.TaskModel;

import java.util.List;

public interface UserTaskView {

    void showTaskList(List<TaskModel> taskModelList);
    void checkpoint();
}
