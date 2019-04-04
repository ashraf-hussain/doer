package com.project.doer.adminReview;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.doer.adminTask.AssignTaskImp;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userReview.ReviewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AdminUserReviewImp implements AdminUserReviewPresenter {
    private Context context;
    private AdminUserReviewView adminUserReviewView;

    AdminUserReviewImp(Context context, AdminUserReviewView adminUserReviewView) {
        this.adminUserReviewView = adminUserReviewView;
        this.context = context;
    }

    @Override
    public void submitUserReviewData(ReviewModel reviewModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);

        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.postReview(reviewModel).enqueue(new Callback<ReviewModel>() {

            final String TAG = AdminUserReviewImp.class.getName();

            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {
                    adminUserReviewView.onSuccess("Task Reviewed");
                }
                adminUserReviewView.onFailed("Sth Wrong");

            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                adminUserReviewView.onFailed("Sth Wrong");

            }
        });
    }
}
