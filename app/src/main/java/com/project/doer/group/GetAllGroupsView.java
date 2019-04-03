package com.project.doer.group;

import java.util.List;

public interface GetAllGroupsView {
    void viewAllGroups(List<GroupModel> groupModelList);

    void onGroupFoundSuccess(String msg);
}
