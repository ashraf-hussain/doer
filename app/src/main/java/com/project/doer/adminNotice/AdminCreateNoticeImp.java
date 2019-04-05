package com.project.doer.adminNotice;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userNotice.EventModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AdminCreateNoticeImp implements AdminCreateNoticePresenter {

    private Context context;
    private AdminCreateNoticeView adminCreateNoticeView;
    //private List<EventModel> eventModelList;
    //private EventDataList eventDataList;

    public AdminCreateNoticeImp(Context context, AdminCreateNoticeView adminCreateNoticeView) {
        this.context = context;
        this.adminCreateNoticeView = adminCreateNoticeView;
    }

    @Override
    public void uploadCreatedEvent(EventModel eventModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.postUserEvents(eventModel).enqueue(new Callback<EventModel>() {
            private final String TAG = AdminCreateNoticeImp.class.getName();

            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code() + "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());
                if (response.isSuccessful()) {

                    adminCreateNoticeView.onSuccess("Event Uploaded" + " " + response.code() + "");

                }
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                adminCreateNoticeView.onFailure("Sth Wrong");
            }
        });
    }
}
