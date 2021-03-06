package com.project.doer.adminTask;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskModel implements Serializable {


    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")

    private String description;
    @SerializedName("deadline")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @SerializedName("time")

    private String deadline;
    @SerializedName("group_id")

    private Integer groupId;
    @SerializedName("notify")

    private Boolean notify;

    @SerializedName("is_reviewed")

    private Boolean isReviewed;

    public Boolean getReviewed() {
        return isReviewed;
    }

    public void setReviewed(Boolean reviewed) {
        isReviewed = reviewed;
    }

    public TaskModel(String title, String description, String deadline, Integer groupId,String time,Boolean notify) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.groupId = groupId;
        this.notify = notify;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }
}
