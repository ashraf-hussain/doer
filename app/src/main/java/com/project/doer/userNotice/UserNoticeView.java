package com.project.doer.userNotice;

import java.util.List;

public interface UserNoticeView {
    void showUserNoticeList(List<EventModel> eventModelList);

    void onSuccess(String msg);

    void onError(String msg);

    void checkpoint();
}
