package com.project.doer.userSignUp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.group.GroupModel;
import com.project.doer.login.LoginActivity;
import com.project.doer.signup.SignUpModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


public class SignUpActivity extends BaseActivity implements UserSignUpView {
    public static final String TAG = SignUpActivity.class.getName();
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.et_user_last_name)
    EditText etUserLastName;
    @BindView(R.id.et_user_dob)
    EditText etUserDob;
    @BindView(R.id.et_user_date_joined)
    EditText etUserDateJoined;
    @BindView(R.id.tv_user_group)
    TextView tvUserGroup;
    @BindView(R.id.et_user_email)
    EditText etUserEmail;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.et_user_repeat_password)
    EditText etUserRepeatPassword;
    @BindView(R.id.btn_user_signUp)
    Button btnUserSignUp;
    @BindView(R.id.sv_user_signup)
    ScrollView svUserSignup;
    private Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener joinedDate;
    String userDob, userJoinedDate, userEmail, userFirstName,
            userLastName, userPassword, userConfrimPassword, userGroup, userGroupId, fcmToken;
    UserSignUpPresenter userSignUpPresenter;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected int getLayout() {
        return R.layout.activity_user_signup;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        calendar = Calendar.getInstance();
        datePickerAction();
        datePickAction();
        userSignUpPresenter = new UserSignupImp(this, this);
        SharedPreferences sharedPreferences = this.getSharedPreferences(AppConstants.TOKEN_DATA,
                MODE_PRIVATE);
         fcmToken = sharedPreferences.getString(AppConstants.FCM_TOKEN, "");
        Log.d(TAG, "Fcm: "+fcmToken);
    }

    @Override
    public void onSuccess(String msg) {

        AppUtils.showSnackbar(this, svUserSignup, msg);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onGroupFoundSuccess(String msg) {
        AppUtils.showSnackbar(this, svUserSignup, msg);
    }

    @Override
    public void onGroupFailure(String msg) {
        AppUtils.showSnackbar(this, svUserSignup, msg);
    }

    @Override
    public void onFailure(String msg) {
        AppUtils.showSnackbar(this, svUserSignup, msg);
    }

    @Override
    public void userGroupListResponse(List<GroupModel> groupModelList) {
        Log.d(TAG, "size " + groupModelList.size());
        userGroupId = String.valueOf((groupModelList.get(0).getId()));
        userGroup = (groupModelList.get(0).getGroupName());
        tvUserGroup.setText(groupModelList.get(0).getGroupName());
        Log.d(TAG, "userGroupListResponse: " + groupModelList.get(0).getGroupName());
    }

    @OnClick({R.id.et_user_dob, R.id.et_user_date_joined, R.id.tv_user_group, R.id.btn_user_signUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_user_dob:
                new DatePickerDialog(this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.et_user_date_joined:
                new DatePickerDialog(this, joinedDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.tv_user_group:
                if (etUserDateJoined.length() > 0) {
                    userSignUpPresenter.loadGroupData();
                } else {
                    AppUtils.showSnackbar(this, svUserSignup, "Please select your joined date first.");
                    etUserDateJoined.setError("Required !");
                }

                break;
            case R.id.btn_user_signUp:
                signupProcess();
                break;
        }
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
                String dateFormat = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                etUserDob.setText(simpleDateFormat.format(calendar.getTime()));
                userDob = simpleDateFormat.format(calendar.getTime());
            }

        };
    }

    //DatePicker
    private void datePickAction() {
        //DatePicker
        joinedDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateGroupDate();
            }

            private void updateGroupDate() {
                String dateFormat = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                etUserDateJoined.setText(simpleDateFormat.format(calendar.getTime()));
                userJoinedDate = simpleDateFormat.format(calendar.getTime());
            }


        };
    }


    private void signupProcess() {
        // Validating user's data.
        userEmail = etUserEmail.getText().toString();
        userFirstName = etUserFirstName.getText().toString();
        userLastName = etUserLastName.getText().toString();
        userPassword = etUserPassword.getText().toString();
        userConfrimPassword = etUserRepeatPassword.getText().toString();

        if (userFirstName.isEmpty()) {
            etUserFirstName.setError("Required!");

        } else if (userLastName.isEmpty()) {
            etUserLastName.setError("Required!");

        } else if (userDob.isEmpty()) {
            etUserDob.setError("Required!");

        } else if (userJoinedDate.isEmpty()) {
            etUserDateJoined.setError("Required!");

        } else if (userGroup.isEmpty()) {
            tvUserGroup.setError("Required !");

        } else if (!userEmail.isEmpty()) {
            etUserEmail.setError("Required!");

        } else if (!userEmail.matches(emailPattern)) {
            etUserEmail.setError("Invalid Email!");

        } else if (userPassword.isEmpty()) {
            etUserPassword.setError("Required!");

        } else if (!userConfrimPassword.equalsIgnoreCase(userPassword)) {
            etUserRepeatPassword.setError("Password Mismatch !");

        }

        if (!userFirstName.equalsIgnoreCase("")
                && !userLastName.equalsIgnoreCase("")
                && !userEmail.equalsIgnoreCase("")
                && !userPassword.equalsIgnoreCase("")
                && !userJoinedDate.equalsIgnoreCase("")
                && !userDob.matches("")
                && !userGroup.equalsIgnoreCase("")
                && userConfrimPassword.equalsIgnoreCase(userPassword)) {


            SignUpModel signUpModel = new SignUpModel(userFirstName, userLastName, userEmail,
                    userPassword, userDob, "false", userGroupId, fcmToken);
            userSignUpPresenter.sendSignUpData(signUpModel);
            Log.d(TAG, userFirstName + " " + userLastName + " "
                    + userDob + " " + userPassword + " " + userEmail + " "
                    + userGroup + " " + userJoinedDate + " " + userGroupId);
        }
    }

}
