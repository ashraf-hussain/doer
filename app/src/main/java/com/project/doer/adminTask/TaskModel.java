package com.project.doer.adminTask;

import com.google.gson.annotations.SerializedName;

public class TaskModel {
    @SerializedName("title")

    private String title;
    @SerializedName("description")

    private String description;
    @SerializedName("deadline")

    private String deadline;
    @SerializedName("group_id")

    private Integer groupId;

    public TaskModel(String title, String description, String deadline, Integer groupId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
