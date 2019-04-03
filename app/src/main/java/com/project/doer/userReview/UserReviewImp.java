package com.project.doer.userReview;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class UserReviewImp implements UserReviewPresenter {

    private UserReviewView userReviewView;
    private Context context;

    public UserReviewImp(UserReviewView userReviewView, Context context) {
        this.userReviewView = userReviewView;
        this.context = context;
    }


    @Override
    public void loadUserReview(int taskId, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");
        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getUsersReviews(taskId, userId).enqueue(new Callback<ReviewModel>() {
            private final String TAG = UserReviewImp.class.getName();

            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {

                    userReviewView.showUserReview(response.body());

                }

            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

            }
        });
    }
}
