package com.project.doer.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.project.doer.R;
import com.project.doer.common.BaseActivity;
import com.project.doer.userSignUp.SignUpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_login_email)
    EditText etLoginEmail;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init() {
    notificationBarSetup();
    }


    @OnClick({R.id.btn_signIn, R.id.tv_crete_ac})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                break;
            case R.id.tv_crete_ac:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    //NotificationBar setup
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void notificationBarSetup() {
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }
}
