package com.project.doer.userNotice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserNoticeDetailActivity extends BaseActivity {

    EventModel eventModel;
    @BindView(R.id.tv_event_detail_title)
    TextView tvEventDetailTitle;
    @BindView(R.id.tv_event_detail_venue)
    TextView tvEventDetailVenue;
    @BindView(R.id.tv_event_detail_deadline)
    TextView tvEventDetailDeadline;
    @BindView(R.id.tv_event_detail_description)
    TextView tvEventDetailDescription;
    @BindView(R.id.tv_event_notice_other_info)
    TextView tvEventNoticeOtherInfo;
    @BindView(R.id.cv_user_event_detail_adapter)
    CardView cvUserEventDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_notice_detail;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            eventModel = (EventModel) intent.getSerializableExtra(AppConstants.NOTICE_DETAIL);

            tvEventDetailTitle.setText(eventModel.getTitle());
            tvEventDetailDescription.setText(eventModel.getDescription());
            tvEventDetailVenue.setText(eventModel.getVenue());
            tvEventDetailDeadline.setText(eventModel.getDeadline() + "," + " " + eventModel.getEventTime());
            if (!eventModel.getOtherInfo().equalsIgnoreCase("")) {
                tvEventNoticeOtherInfo.setVisibility(View.VISIBLE);
                tvEventNoticeOtherInfo.setText(eventModel.getOtherInfo());
            }
        }
    }
}
