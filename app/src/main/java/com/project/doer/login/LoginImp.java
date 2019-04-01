package com.project.doer.login;

import android.util.Log;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.model.DoerApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginImp implements LoginPresenter {
    private BaseView baseView;

    public LoginImp(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void sendLoginData(LoginModel loginModel) {

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.actionLogin(loginModel).enqueue(new Callback<LoginModel>() {
            public final String TAG = LoginImp.class.getName();

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {
                    LoginModel loginModelResponse = response.body();
                    String token = loginModelResponse.getToken();
                    Log.d(TAG, "onResponse: " + token);
                    baseView.onSuccess(token);
                }

            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                baseView.onFailure("Sth wrong");
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }
}
