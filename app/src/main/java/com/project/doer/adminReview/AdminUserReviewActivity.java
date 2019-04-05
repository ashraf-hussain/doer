package com.project.doer.adminReview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.project.doer.R;
import com.project.doer.adminTask.AdminTaskListActivity;
import com.project.doer.allUser.AllUserActivity;
import com.project.doer.allUser.AllUsersModel;
import com.project.doer.common.BaseActivity;
import com.project.doer.data.AppConstants;
import com.project.doer.data.AppUtils;
import com.project.doer.userReview.ReviewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminUserReviewActivity extends BaseActivity implements AdminUserReviewView {

    String taskTitle, review, performanceNote;
    int marks;
    AllUsersModel allUsersModel;
    int userId, taskId;
    AdminUserReviewPresenter adminUserReviewPresenter;
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

    @Override
    protected int getLayout() {
        return R.layout.activity_admin_uplod_review;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        taskId = AllUserActivity.taskId;

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            allUsersModel = (AllUsersModel) intent.getSerializableExtra(AppConstants.All_USER);
            userId = Integer.parseInt(allUsersModel.getId());
            Log.d("user", "init: " + userId);

            adminUserReviewPresenter = new AdminUserReviewImp(this, this);
        }
    }

    @OnClick(R.id.btn_upload_review)
    public void onViewClicked() {
        submitUserReview();
    }

    private void submitUserReview() {
        taskTitle = etReviewTitle.getText().toString();
        marks = Integer.parseInt(etReviewMarks.getText().toString());
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

            if (!taskTitle.equalsIgnoreCase("")
                    && etReviewMarks.length() > 0
                    && !review.equalsIgnoreCase("")
                    && !performanceNote.equalsIgnoreCase("")) {

                ReviewModel reviewModel = new ReviewModel(1, userId, taskId, marks,
                        review, 1, performanceNote, "2019-09-09", true);
                adminUserReviewPresenter.submitUserReviewData(reviewModel);

            }

        }
    }

    @Override
    public void onSuccess(String msg) {
        AppUtils.showToast(this, msg);
        Intent i = new Intent(this, AdminTaskListActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onFailed(String msg) {
        AppUtils.showToast(this, msg);

    }

}
