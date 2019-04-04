package com.project.doer.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.group.GetAllGroupsActivity;
import com.project.doer.superAdmin.SuperAdminActivity;
import com.project.doer.user.UserDashboardActivity;
import com.project.doer.userSignUp.SignUpActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements BaseView {
    @BindView(R.id.et_login_email)
    EditText etLoginEmail;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    @BindView(R.id.tv_admin_sign_in)
    TextView tvAdminSignUp;
    String email, password;
    LoginPresenter loginPresenter;
    SharedPreferences sharedPreferences;
    @BindView(R.id.sv_assign_task)
    ScrollView svLogin;
    public static int userGroupId, idOfUser;
    private static final String TAG = LoginActivity.class.getName();


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        fcmAction();
        isLoggedIn();

        loginPresenter = new LoginImp(this, this);

        tvAdminSignUp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intentAdmin = new Intent(LoginActivity.this, AdminSignUpActivity.class);
                startActivity(intentAdmin);
                finish();
                return true;
            }
        });
    }

    private void isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        Log.d(TAG, "isLoggedIn: " + token);
//
//        if (token.equalsIgnoreCase("")) {
//            Intent intent = new Intent(LoginActivity.this, GetAllGroupsActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        if (token.equalsIgnoreCase("")) {
//            Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }


    @OnClick({R.id.btn_signIn, R.id.tv_crete_ac})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                processLogin();
                break;
            case R.id.tv_crete_ac:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(String token, String role, int groupId) {
        AppUtils.showToast(this, role);
        save(AppConstants.TOKEN, token);
        userGroupId = groupId;
       // idOfUser = userId;

        if (role.equalsIgnoreCase("USER")) {
            Log.d(TAG, "onSuccess: " + role);
            Intent userIntent = new Intent(this, UserDashboardActivity.class);
            startActivity(userIntent);
            finish();

        } else if (role.equalsIgnoreCase("ADMIN")) {
            Log.d(TAG, "onSuccess: " + role);

            Intent admin = new Intent(this, GetAllGroupsActivity.class);
            startActivity(admin);
            finish();

        } else if (role.equalsIgnoreCase("ROOT")) {
            Log.d(TAG, "onSuccess: " + role);

            Intent root = new Intent(this, SuperAdminActivity.class);
            startActivity(root);
            finish();
        }

    }

    @Override
    public void onFailure(String msg) {
        AppUtils.showSnackbar(this, svLogin, msg);

    }

    private void processLogin() {
        email = etLoginEmail.getText().toString().trim();
        password = etLoginPassword.getText().toString().trim();
        if (email.isEmpty()) {
            etLoginEmail.setError("Required!");

        } else if (password.isEmpty()) {
            etLoginPassword.setError("Required!");

        }

        if (!email.equalsIgnoreCase("")
                && !password.equalsIgnoreCase("")) {
            LoginModel loginModel = new LoginModel(email, password);
            loginPresenter.sendLoginData(loginModel);
        }
    }


    private void fcmAction() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {

                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //To do//

                            return;
                        }

                        // Get the Instance ID token//
                        String token = task.getResult().getToken();
                        save(AppConstants.FCM_TOKEN, token);

                        Log.d(TAG, "fcm" + " " + token);

                    }
                });
    }

    void save(String tag, String value) {
        sharedPreferences = getSharedPreferences(AppConstants.TOKEN_DATA,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag, value);
        editor.commit();
    }

}
