package com.project.doer.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppUtils;
import com.project.doer.signup.SignUpModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminSignUpActivity extends BaseActivity implements AdminSignUpView {
    public static final String TAG = AdminSignUpActivity.class.getName();
    @BindView(R.id.et_admin_first_name)
    EditText etAdminFirstName;
    @BindView(R.id.et_admin_last_name)
    EditText etAdminLastName;
    @BindView(R.id.et_admin_dob)
    EditText etAdminDob;
    @BindView(R.id.et_admin_email)
    EditText etAdminEmail;
    @BindView(R.id.et_admin_password)
    EditText etAdminPassword;
    @BindView(R.id.et_admin_repeat_password)
    EditText etAdminRepeatPassword;
    @BindView(R.id.btn_admin_signUp)
    Button btnAdminSignUp;
    @BindView(R.id.sv_admin_signUp)
    ScrollView svAdminSignUp;

    private Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    String adminDob, adminEmail, adminFirstName,
            adminLastName, adminPassword, adminConfrimPassword;
    AdminSignUpPresenter adminSignUpPresenter;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected int getLayout() {
        return R.layout.activity_admin_signup;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        calendar = Calendar.getInstance();
        birthDatePickerAction();
        adminSignUpPresenter = new AdminSignUpImp(this);
    }

    @Override
    public void onSuccess(String msg) {

        AppUtils.showSnackbar(this, svAdminSignUp, msg);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onFailure(String msg) {
        AppUtils.showSnackbar(this, svAdminSignUp, msg);
    }


    @OnClick({R.id.et_admin_dob, R.id.btn_admin_signUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_admin_dob:
                new DatePickerDialog(this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_admin_signUp:
                signUpProcess();
                break;
        }
    }


    //DatePicker
    private void birthDatePickerAction() {
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

                etAdminDob.setText(simpleDateFormat.format(calendar.getTime()));
                adminDob = simpleDateFormat.format(calendar.getTime());
            }

        };
    }


    private void signUpProcess() {
        // Validating admin's data.
        adminEmail = etAdminEmail.getText().toString();
        adminFirstName = etAdminFirstName.getText().toString();
        adminLastName = etAdminLastName.getText().toString();
        adminPassword = etAdminPassword.getText().toString();
        adminConfrimPassword = etAdminRepeatPassword.getText().toString();

        if (adminFirstName.isEmpty()) {
            etAdminFirstName.setError("Required!");

        } else if (adminLastName.isEmpty()) {
            etAdminLastName.setError("Required!");

        } else if (adminDob.isEmpty()) {
            etAdminDob.setError("Required!");

        } else if (!adminEmail.isEmpty()) {
            etAdminEmail.setError("Required!");

        } else if (!adminEmail.matches(emailPattern)) {
            etAdminEmail.setError("Invalid Email!");

        } else if (adminPassword.isEmpty()) {
            etAdminPassword.setError("Required!");

        } else if (!adminConfrimPassword.equalsIgnoreCase(adminPassword)) {
            etAdminRepeatPassword.setError("Password Mismatch !");

        }

        if (!adminFirstName.equalsIgnoreCase("")
                && !adminLastName.equalsIgnoreCase("")
                && !adminEmail.equalsIgnoreCase("")
                && !adminPassword.equalsIgnoreCase("")
                && !adminDob.matches("")
                && adminConfrimPassword.equalsIgnoreCase(adminPassword)) {


            SignUpModel adminSignUpModel = new SignUpModel(adminFirstName, adminLastName, adminEmail,
                    adminPassword, adminDob, "true");
            adminSignUpPresenter.sendAdminSignUpData(adminSignUpModel);
            Log.d(TAG, adminFirstName + " " + adminLastName + " "
                    + adminDob + " " + adminPassword + " " + adminEmail);
        }
    }
}