package com.project.doer;

import com.google.gson.annotations.SerializedName;
import com.project.doer.group.GroupModel;

import java.util.List;

public class GroupList {
    public List<GroupModel> getGroupLists() {
        return groupLists;
    }

    @SerializedName("data")
    private List<GroupModel> groupLists;
}

