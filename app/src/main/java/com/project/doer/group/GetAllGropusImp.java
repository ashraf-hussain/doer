package com.project.doer.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.doer.GroupList;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userSignUp.UserSignupImp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class GetAllGropusImp implements GetAllGroupsPresenter {
    private GetAllGroupsView getAllGroupsView;
    private List<GroupModel> groupModelList;
    private Context context;

    public GetAllGropusImp(GetAllGroupsView getAllGroupsView, Context context) {
        this.getAllGroupsView = getAllGroupsView;
        this.context = context;
    }

    @Override
    public void loadAllGroups() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getGroups().enqueue(new Callback<GroupList>() {
            private final String TAG = GetAllGropusImp.class.getName();

            @Override
            public void onResponse(Call<GroupList> call, Response<GroupList> response) {
                Log.d(TAG, "onResponseGroup: " + response.code());
                Log.d(TAG, "onResponseGroup: " + response.raw().request().url());
                if (response.code() == 200) {

                    GroupList groupList = response.body();
                    groupModelList = groupList.getGroupLists();

                    getAllGroupsView.viewAllGroups(groupModelList);
                    getAllGroupsView.onGroupFoundSuccess("Group Loaded");
                }
            }

            @Override
            public void onFailure(Call<GroupList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }
}
