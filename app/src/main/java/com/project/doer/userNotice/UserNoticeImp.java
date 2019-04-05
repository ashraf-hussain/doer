package com.project.doer.userNotice;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class UserNoticeImp implements UserNoticePresenter {

    private Context context;
    private UserNoticeView userNoticeView;
    private UserEventList userEventList;
    List<EventModel> eventModelList;

    public UserNoticeImp(Context context, UserNoticeView userNoticeView) {
        this.context = context;
        this.userNoticeView = userNoticeView;
    }

    @Override
    public void loadUserEventNotice() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");
        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getUserEvents().enqueue(new Callback<UserEventList>() {
            @Override
            public void onResponse(Call<UserEventList> call, Response<UserEventList> response) {
                final String TAG = UserNoticeImp.class.getName();
                Toast.makeText(context, response.code() + "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {

                    userEventList = response.body();
                    eventModelList = userEventList.getEventModelList();
                    userNoticeView.showUserNoticeList(eventModelList);
                    userNoticeView.onSuccess("Events Loaded");
                }
            }

            @Override
            public void onFailure(Call<UserEventList> call, Throwable t) {
                userNoticeView.onError("Sth Wrong");

            }
        });
    }
}