package com.project.doer.allUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.project.doer.R;
import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userReview.ReviewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminUserFeebackActivity extends AppCompatActivity {
    private static final String TAG = AdminUserFeebackActivity.class.getName();

    String taskTitle, review, performanceNote;
    String marks;
    AllUsersModel allUsersModel;
    int userId, taskId;
    @BindView(R.id.et_review_title)
    EditText etReviewTitle;
    @BindView(R.id.et_review_marks)
    EditText etReviewMarks;
    @BindView(R.id.et_user_review)
    EditText etUserReview;
    @BindView(R.id.et_review_performance_note)
    EditText etReviewPerformanceNote;
    @BindView(R.id.btn_upload_review)
    Button btnUploadReview;
    @BindView(R.id.sv_review_task)
    ScrollView svReviewTask;


    @OnClick(R.id.btn_upload_review)
    public void onViewClicked() {
        processRetrofit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_uplod_review);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        taskId = AllUserActivity.taskId;

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            allUsersModel = (AllUsersModel) intent.getSerializableExtra(AppConstants.All_USER);
            userId = Integer.parseInt(allUsersModel.getId());
            Log.d(TAG, "user:" + userId);

        }


    }


    private void processRetrofit() {
        Log.d(TAG, "processRetrofit: " + "t");
        taskTitle = etReviewTitle.getText().toString();
        marks = etReviewMarks.getText().toString();
        review = etUserReview.getText().toString();
        performanceNote = etReviewPerformanceNote.getText().toString();

        if (taskTitle.isEmpty()) {
            etReviewTitle.setError("Required!");

        } else if (etReviewMarks.length() == 0) {
            etReviewMarks.setError("Required!");

        } else if (review.isEmpty()) {
            etUserReview.setError("Required!");

        } else if (performanceNote.isEmpty()) {
            etReviewPerformanceNote.setError("Required!");
        }

        if (!taskTitle.equalsIgnoreCase("")
                && etReviewMarks.length() > 0
                && !review.equalsIgnoreCase("")
                && !performanceNote.equalsIgnoreCase("")) {
            ReviewModel reviewModel = new ReviewModel(2, userId, taskId, Integer.parseInt(marks),
                    review, 1, performanceNote, "2019-09-09", true);

            //adminUserReviewPresenter.submitUserReviewData(reviewModel);
            Log.d(TAG, "processRetrofit: " + "r");

            SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);
            String token = sharedPreferences.getString(AppConstants.TOKEN, "");

            SetupRetrofit setupRetrofit = new SetupRetrofit();
            Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);

            DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
            doerApiInterface.postReview(reviewModel).enqueue(new Callback<ReviewModel>() {

                //final String TAG = AdminUserReviewImp.class.getName();

                @Override
                public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                    Log.d(TAG, "onResponse: " + response.code());
                    Toast.makeText(AdminUserFeebackActivity.this, response.code() + "",
                            Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.raw().request().url());
                    Log.d(TAG, "processRetrofit: " + "u");

                    if (response.code() == 200) {
                        Toast.makeText(AdminUserFeebackActivity.this, "Task Reviewed",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ReviewModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                    Log.d(TAG, "processRetrofit: " + "v");

                    //adminUserReviewView.onFailed("Sth Wrong");
                    Toast.makeText(AdminUserFeebackActivity.this, "Sth wrong",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.back_button)
    public void onClicked() {
        onBackPressed();
    }
}



