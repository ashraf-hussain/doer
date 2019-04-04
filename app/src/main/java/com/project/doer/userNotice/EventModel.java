package com.project.doer.userNotice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventModel implements Serializable {


    @SerializedName("title")

    private String title;
    @SerializedName("description")

    private String description;
    @SerializedName("venue")

    private String venue;
    @SerializedName("event_img")

    private String eventImg;
    @SerializedName("event_time")

    private String eventTime;
    @SerializedName("other_info")

    private String otherInfo;
    @SerializedName("deadline")

    private String deadline;

    @SerializedName("notify")
    private Boolean notify;

    public EventModel(String title, String description, String venue, String eventImg,
                      String eventTime, String otherInfo, String deadline, Boolean notify) {
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventImg = eventImg;
        this.eventTime = eventTime;
        this.otherInfo = otherInfo;
        this.deadline = deadline;
        this.notify = notify;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }


}
