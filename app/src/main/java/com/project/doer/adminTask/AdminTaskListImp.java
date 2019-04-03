package com.project.doer.adminTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userTask.UserTasklist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AdminTaskListImp implements AdminTaskListPresenter {

    private List<TaskModel> taskLists;
    private UserTasklist userTasklist;
    private AdminTaskListView adminTaskListView;
    private Context context;

    AdminTaskListImp(AdminTaskListView adminTaskListView, Context context) {
        this.adminTaskListView = adminTaskListView;
        this.context = context;
    }

    @Override
    public void loadAllCreatedTask(int groupId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getAllGroupTask(groupId).enqueue(new Callback<UserTasklist>() {
            @Override
            public void onResponse(Call<UserTasklist> call, Response<UserTasklist> response) {
                final String TAG = AssignTaskImp.class.getName();

                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());
                if (response.isSuccessful()) {

                    userTasklist = response.body();
                    taskLists = userTasklist.getTaskList();
                    adminTaskListView.showAllTaskCreated(taskLists);
                }
            }

            @Override
            public void onFailure(Call<UserTasklist> call, Throwable t) {

            }
        });
    }
}
