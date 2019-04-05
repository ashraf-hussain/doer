package com.project.doer.adminNotice;

import com.google.gson.annotations.SerializedName;
import com.project.doer.userNotice.EventModel;

import java.util.List;

public class EventDataList {

    public List<EventModel> getModelList() {
        return modelList;
    }

    @SerializedName("data")
    private List<EventModel> modelList;
}
