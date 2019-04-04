package com.project.doer.userReview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.doer.R;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserReviewActivity extends AppCompatActivity {

    TaskModel taskModel;
    public static final String TAG = UserReviewActivity.class.getName();
    @BindView(R.id.tv_no_data_yet)
    TextView tvNoDataYet;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.tv_review_title)
    TextView tvReviewTitle;
    @BindView(R.id.tv_review_marks)
    TextView tvReviewMarks;
    @BindView(R.id.tv_review_task)
    TextView tvReviewTask;
    @BindView(R.id.tv_review_performance)
    TextView tvReviewPerformance;
    @BindView(R.id.tv_review_rating)
    TextView tvReviewRating;
    @BindView(R.id.cv_user_review_adapter)
    CardView cvUserReviewAdapter;
    @BindView(R.id.ll_user_review_adapter)
    LinearLayout llUserReviewAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            taskModel = (TaskModel) intent.getSerializableExtra(AppConstants.TASK_DETAIL);
            Log.d(TAG, "showUserReviewIn: " + taskModel.getTitle());
        }
        tvReviewTitle.setText(taskModel.getTitle());

        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getUsersReviews(Integer.parseInt(taskModel.getId()), 3).enqueue(new Callback<ReviewModel>() {
            private final String TAG = UserReviewImp.class.getName();

            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(UserReviewActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());

                if (response.isSuccessful()) {
                    ReviewModel reviewModel = response.body();
                    cvUserReviewAdapter.setVisibility(View.VISIBLE);
                    tvReviewMarks.setText("Marks: " + reviewModel.getMarks() + "");
                    tvReviewTask.setText(reviewModel.getReview());
                    tvReviewPerformance.setText(reviewModel.getPerformanceNote());
                    // tvReviewRating.setText(String.valueOf(reviewModel.getRating()));

                }
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

                Log.d(TAG, "onResponseFail: " + t);

            }
        });

    }

}
