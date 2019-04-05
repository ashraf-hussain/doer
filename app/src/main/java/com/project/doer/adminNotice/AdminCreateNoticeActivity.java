package com.project.doer.adminNotice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;

import com.project.doer.R;
import com.project.doer.admin.AdminLandingActivity;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppUtils;
import com.project.doer.userNotice.EventModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminCreateNoticeActivity extends BaseActivity implements AdminCreateNoticeView {
    @BindView(R.id.et_event_title)
    EditText etEventTitle;
    @BindView(R.id.et_event_venue)
    EditText etEventVenue;
    @BindView(R.id.et_event_date)
    EditText etEventDate;
    @BindView(R.id.et_event_time)
    EditText etEventTime;
    @BindView(R.id.et_event_description)
    EditText etEventDescription;
    @BindView(R.id.et_event_other_info)
    EditText etEventOtherInfo;
    @BindView(R.id.btn_upload_event)
    Button btnUploadEvent;
    @BindView(R.id.sv_event_task)
    ScrollView svEventTask;
    private Calendar calendar;
    private int mHour, mMinute;
    private DatePickerDialog.OnDateSetListener date;
    String title, eventDate, venue, description, otherInfo, eventTime;
    AdminCreateNoticePresenter createNoticePresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_admin_create_notice;
    }

    @Override
    protected void init() {
        calendar = Calendar.getInstance();
        datePickerAction();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        createNoticePresenter = new AdminCreateNoticeImp(this, this);

    }

    @OnClick({R.id.et_event_date, R.id.et_event_time, R.id.btn_upload_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_event_date:
                new DatePickerDialog(AdminCreateNoticeActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.et_event_time:
                timePickerAction();
                break;
            case R.id.btn_upload_event:
                processUploadingEvent();
                break;
        }
    }

    //TimePicker
    private void timePickerAction() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String time;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            time = hourOfDay + ":" + minute + " AM";
                        } else {
                            if (hourOfDay == 12) {
                                time = hourOfDay + ":" + minute + " PM";
                            } else {
                                hourOfDay = hourOfDay - 12;
                                time = hourOfDay + ":" + minute + " PM";
                            }
                        }

                        etEventTime.setText(time);
                        eventTime = time;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    //DatePicker
    private void datePickerAction() {
        //DatePicker
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

            private void updateDate() {
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                etEventDate.setText(simpleDateFormat.format(calendar.getTime()));
                eventDate = simpleDateFormat.format(calendar.getTime());
            }
        };
    }

    private void processUploadingEvent() {
        title = etEventTitle.getText().toString().trim();
        venue = etEventVenue.getText().toString().trim();
        eventDate = etEventDate.getText().toString().trim();
        eventTime = etEventTime.getText().toString().trim();
        description = etEventDescription.getText().toString().trim();
        otherInfo = etEventOtherInfo.getText().toString().trim();

        if (title.isEmpty()) {
            etEventTitle.setError("Required!");

        } else if (venue.isEmpty()) {
            etEventVenue.setError("Required!");

        } else if (eventDate.isEmpty()) {
            etEventDate.setError("Required!");

        }else if (eventTime.isEmpty()) {
            etEventDate.setError("Required!");

        } else if (description.isEmpty()) {
            etEventDescription.setError("Required!");

        }

        if (!title.equalsIgnoreCase("")
                && !venue.equalsIgnoreCase("")
                && !eventDate.equalsIgnoreCase("")
                && !description.equalsIgnoreCase("")) {
            EventModel eventModel = new EventModel(title, description, venue, eventTime, otherInfo,
                    eventDate, true);
            createNoticePresenter.uploadCreatedEvent(eventModel);
        }

    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(this, msg);
        Intent i = new Intent(this, AdminLandingActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onFailure(String msg) {

    }
}
