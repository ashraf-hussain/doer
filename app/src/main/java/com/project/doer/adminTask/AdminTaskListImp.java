package com.project.doer.adminTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userTask.UserDatalist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AdminTaskListImp implements AdminTaskListPresenter {

    private List<TaskModel> taskLists;
    private UserDatalist userDatalist;
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
        doerApiInterface.getGroupTask(groupId).enqueue(new Callback<UserDatalist>() {
            @Override
            public void onResponse(Call<UserDatalist> call, Response<UserDatalist> response) {
                final String TAG = AssignTaskImp.class.getName();

                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code()+"", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());
                if (response.isSuccessful()) {

                    userDatalist = response.body();
                    taskLists = userDatalist.getTaskList();
                    adminTaskListView.showAllTaskCreated(taskLists);
                }
            }

            @Override
            public void onFailure(Call<UserDatalist> call, Throwable t) {

            }
        });
    }
}
