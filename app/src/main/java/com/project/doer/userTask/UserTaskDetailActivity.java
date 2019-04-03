package com.project.doer.userTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.userReview.ReviewModel;
import com.project.doer.userReview.UserReviewImp;
import com.project.doer.userReview.UserReviewPresenter;
import com.project.doer.userReview.UserReviewView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTaskDetailActivity extends BaseActivity implements UserReviewView {
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
    TaskModel taskModel;
    @BindView(R.id.tv_no_data_yet)
    TextView tvNoDataYet;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.ll_user_review_adapter)
    LinearLayout llUserReviewAdapter;
    UserReviewPresenter userReviewPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_review;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            taskModel = (TaskModel) intent.getSerializableExtra(AppConstants.TASK_DETAIL);
        }

        userReviewPresenter = new UserReviewImp(this, this);
    }

    @Override
    public void showUserReview(ReviewModel reviewModel) {
        userReviewPresenter.loadUserReview(reviewModel.getTaskId(), reviewModel.getRevieweeId());

        if (!reviewModel.getReview().equalsIgnoreCase("")) {
            cvUserReviewAdapter.setVisibility(View.VISIBLE);
            tvReviewMarks.setText(taskModel.getTitle());
            tvReviewMarks.setText(reviewModel.getMarks() + "");
            tvReviewMarks.setText("Marks: " + reviewModel.getMarks() + "");
            tvReviewMarks.setText(reviewModel.getReview());
            tvReviewMarks.setText(reviewModel.getPerformanceNote());
            tvReviewMarks.setText(String.valueOf(reviewModel.getRating()));
        } else {
            cvUserReviewAdapter.setVisibility(View.GONE);

        }
    }

    @Override
    public void checkpoint() {

    }

}
