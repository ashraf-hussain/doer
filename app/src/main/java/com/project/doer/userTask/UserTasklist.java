package com.project.doer.userTask;

import com.google.gson.annotations.SerializedName;
import com.project.doer.adminTask.TaskModel;

import java.util.List;

public class UserTasklist {
    public List<TaskModel> getTaskList() {
        return taskLists;
    }

    @SerializedName("data")
    private List<TaskModel> taskLists;
}
