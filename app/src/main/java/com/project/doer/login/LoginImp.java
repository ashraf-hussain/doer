package com.project.doer.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.group.GroupModel;
import com.project.doer.model.DoerApiInterface;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginImp implements LoginPresenter {
    private BaseView baseView;
    private Context context;

    public LoginImp(BaseView baseView, Context context) {
        this.baseView = baseView;
        this.context = context;
    }


    @Override
    public void sendLoginData(final LoginModel loginModel) {

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.actionLogin(loginModel).enqueue(new Callback<LoginModel>() {
            final String TAG = LoginImp.class.getName();

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code() + "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {
                    LoginModel loginModelResponse = response.body();
                    //   int userId = Integer.parseInt(loginModelResponse.getId()+"");
                    String token = loginModelResponse.getToken();

                    RoleModel roleModel = loginModelResponse.getRoleModel();
                    String role = roleModel.getRole();

                    GroupModel groupModel = loginModelResponse.getGroupModel();

                    int groupId = groupModel.getId();
                    baseView.onSuccess(token, role, groupId);

                    Log.d(TAG, "onResponse: " + token + " " + role + " " + groupId);
                }
                if (response.code() == 400) {

                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
