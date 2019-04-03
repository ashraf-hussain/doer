package com.project.doer.group;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GroupModel implements Serializable {
    @SerializedName("id")

    private Integer id;
    @SerializedName("group_name")

    private String groupName;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("platform")

    private String platform;
    @SerializedName("start_date")

    private String startDate;
    @SerializedName("modified_date")

    private Object modifiedDate;
    @SerializedName("status")

    private Boolean status;

    public GroupModel(String groupName, String platform, String startDate) {
        this.groupName = groupName;
        this.platform = platform;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
