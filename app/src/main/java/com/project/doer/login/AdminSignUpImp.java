package com.project.doer.login;

import android.util.Log;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.signup.SignUpModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminSignUpImp implements AdminSignUpPresenter {

    private AdminSignUpView adminSignUpView;

    public AdminSignUpImp(AdminSignUpView adminSignUpView) {
        this.adminSignUpView = adminSignUpView;
    }

    @Override
    public void sendAdminSignUpData(SignUpModel signUpModel) {
        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.actionAdminSignup(signUpModel).enqueue(new Callback<SignUpModel>() {
            private final String TAG = AdminSignUpImp.class.getName();

            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {

                    adminSignUpView.onSuccess("User Created");
                    adminSignUpView.onSuccess(String.valueOf(response.code()));
                }

                if (response.code() == 400) {
                    try {
                        Log.v("Error code 400", response.errorBody().string());
                        adminSignUpView.onFailure(String.valueOf(response.code()));
                        adminSignUpView.onFailure(response.errorBody().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {

            }
        });
    }
}
