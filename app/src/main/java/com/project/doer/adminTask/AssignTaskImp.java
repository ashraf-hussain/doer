package com.project.doer.adminTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AssignTaskImp implements AssignTaskPresenter {

    private AssignTaskView assignTaskView;
    private Context context;

    AssignTaskImp(AssignTaskView assignTaskView, Context context) {
        this.assignTaskView = assignTaskView;
        this.context = context;
    }


    @Override
    public void uploadTask(TaskModel taskModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");
        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.postTask(taskModel).enqueue(new Callback<TaskModel>() {
            final String TAG = AssignTaskImp.class.getName();

            @Override
            public void onResponse(Call<TaskModel> call, Response<TaskModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code()+"", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {
                    assignTaskView.onSuccess("Task Uploaded");
                }
                assignTaskView.onError("Sth Wrong");

            }

            @Override
            public void onFailure(Call<TaskModel> call, Throwable t) {
                assignTaskView.onError("On Failure");
            }
        });
    }
}
