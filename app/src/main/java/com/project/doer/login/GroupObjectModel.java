package com.project.doer.login;

import com.google.gson.annotations.SerializedName;
import com.project.doer.group.GroupModel;

public class GroupObjectModel {

    public GroupModel getGroupModel() {
        return groupModel;
    }

    @SerializedName("group")
    private GroupModel groupModel;

}
