package com.project.doer.userSignUp;

import android.util.Log;

import com.project.doer.GroupList;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.group.GroupModel;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.signup.SignUpModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserSignupImp implements UserSignUpPresenter {

    private UserSignUpView view;
    private List<GroupModel> groupModelList;

    UserSignupImp(UserSignUpView view) {
        this.view = view;
    }

    @Override
    public void sendSignUpData(SignUpModel signUpModel) {


        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.actionUserSignup(signUpModel).enqueue(new Callback<SignUpModel>() {
            private final String TAG = UserSignupImp.class.getName();

            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {

                    view.onSuccess("User Created");
                    view.onSuccess(String.valueOf(response.code()));
                }

                if (response.code() == 400) {
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                        view.onFailure(response.code();
                        view.onFailure(response.errorBody().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public void loadGroupData() {
        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getGroups().enqueue(new Callback<GroupList>() {
            private final String TAG = UserSignupImp.class.getName();

            @Override
            public void onResponse(Call<GroupList> call, Response<GroupList> response) {
                Log.d(TAG, "onResponseGroup: " + response.code());
                Log.d(TAG, "onResponseGroup: " + response.raw().request().url());
                if (response.code() == 200) {

                    GroupList groupList = response.body();
                    groupModelList = groupList.getGroupLists();

                    view.userGroupListResponse(groupModelList);
                    view.onSuccess("Group Found");
                }
                //view.onSuccess("Group not found from your joined date");
            }

            @Override
            public void onFailure(Call<GroupList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

}
