package com.project.doer.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.login.AdminSignUpImp;
import com.project.doer.model.DoerApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class CreateGroupImp implements CreateGroupPresenter {

    public CreateGroupImp(CreateGroupView createGroupView, Context context) {
        this.createGroupView = createGroupView;
        this.context = context;
    }

    private CreateGroupView createGroupView;
    private Context context;

    @Override
    public void sendCreateGroupData(GroupModel groupModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.createGroup(groupModel).enqueue(new Callback<GroupModel>() {
            private final String TAG = CreateGroupImp.class.getName();

            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {

                    createGroupView.onSuccess("Group Created");
                    createGroupView.onSuccess(String.valueOf(response.code()));
                }

                if (response.code() == 500) {
                    try {
                        Log.v("Error code 400", response.errorBody().string());
                        createGroupView.onFailure(String.valueOf(response.code()));
                        createGroupView.onFailure(response.errorBody().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {
                createGroupView.onFailure("Something wrong!");
            }
        });

    }
}
