package com.project.doer.userTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.adminTask.TaskModel;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class UserTaskImp implements UserTaskPresenter {
    private UserTaskView userTaskView;
    private List<TaskModel> taskLists;
    private UserTasklist userTasklist;
    private Context context;


   public UserTaskImp(UserTaskView userTaskView, Context context) {
        this.userTaskView = userTaskView;
        this.context = context;
    }

    @Override
    public void loadUserTask(int groupId) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getGroupTask(groupId).enqueue(new Callback<UserTasklist>() {
            private final String TAG = UserTaskImp.class.getName();

            @Override
            public void onResponse(Call<UserTasklist> call, Response<UserTasklist> response) {

                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code()+"", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());
                if (response.isSuccessful()) {

                    userTasklist = response.body();
                    taskLists = userTasklist.getTaskList();
                    userTaskView.showTaskList(taskLists);
                }
            }

            @Override
            public void onFailure(Call<UserTasklist> call, Throwable t) {

            }
        });
    }
}
