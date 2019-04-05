package com.project.doer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppUtils;
import com.project.doer.group.CreateGroupImp;
import com.project.doer.group.CreateGroupPresenter;
import com.project.doer.group.CreateGroupView;
import com.project.doer.group.CustomAdapter;
import com.project.doer.group.GetAllGroupsActivity;
import com.project.doer.group.GroupModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGroupActivity extends BaseActivity implements AdapterView.OnItemSelectedListener
        , CreateGroupView {
    @BindView(R.id.et_group_start_date)
    EditText etGroupStartDate;
    @BindView(R.id.spinner_platform_list)
    Spinner spinnerPlatformList;
    @BindView(R.id.et_group_name)
    EditText etGroupName;
    String[] platformNames;
    String userPlatformSelection, groupName, startDate;
    CreateGroupPresenter createGroupPresenter;
    DatePickerDialog.OnDateSetListener date;
    Calendar calendar;

    @Override
    protected int getLayout() {
        return R.layout.activity_create_new_group;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        calendar = Calendar.getInstance();
        //etGroupStartDate.setEnabled(false);
        spinnerPlatformList.setPrompt(getResources().getString(R.string.select_platform));

        //Spinner drop down elements
        platformNames = new String[]{"Android", "Ios", "Not Specified"};

        int flags[] = {R.drawable.ic_android_black_24dp, R.drawable.ic_ios, R.drawable.ic_arrow_drop_down_circle_black_24dp};

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, platformNames);

        spinnerPlatformList.setAdapter(customAdapter);
        spinnerPlatformList.setSelection(customAdapter.getCount());
        spinnerPlatformList.setOnItemSelectedListener(this);
        customAdapter.notifyDataSetChanged();

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
                etGroupStartDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };


        createGroupPresenter = new CreateGroupImp(this, this);
    }

    private void processGroup() {
        groupName = etGroupName.getText().toString().trim();
        startDate = etGroupStartDate.getText().toString().trim();


        if (startDate.isEmpty()) {
            etGroupStartDate.setError("Date Required !");

        } else if (userPlatformSelection.isEmpty()) {
            Toast.makeText(this, "Please select platform.", Toast.LENGTH_SHORT).show();


        } else if (groupName.isEmpty()) {
            etGroupStartDate.setError("Name Required !");
        }

        if (!startDate.equalsIgnoreCase("")
                && !userPlatformSelection.equalsIgnoreCase("Not Specified")
                && !groupName.equalsIgnoreCase("")) {

            GroupModel groupModel = new GroupModel(groupName, userPlatformSelection, startDate);
            createGroupPresenter.sendCreateGroupData(groupModel);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), platformNames[position], Toast.LENGTH_LONG).show();
        userPlatformSelection = platformNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(this, msg);
        Intent i = new Intent(CreateGroupActivity.this, GetAllGroupsActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onFailure(String msg) {

    }

    @OnClick({R.id.et_group_start_date, R.id.btn_create_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_group_start_date:
                new DatePickerDialog(this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_create_group:
                processGroup();
                break;
        }
    }

    @OnClick(R.id.back_button)
    public void onViewClicked() {
        onBackPressed();
    }
}
