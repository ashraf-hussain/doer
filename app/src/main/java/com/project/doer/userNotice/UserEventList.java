package com.project.doer.userNotice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserEventList {
    public List<EventModel> getEventModelList() {
        return eventModelList;
    }

    @SerializedName("data")
    private List<EventModel> eventModelList;
}
