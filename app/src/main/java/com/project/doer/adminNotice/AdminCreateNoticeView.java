package com.project.doer.adminNotice;

import com.project.doer.userNotice.EventModel;

public interface AdminCreateNoticeView {
    void onSuccess(String msg);
    void onFailure(String msg);
}
