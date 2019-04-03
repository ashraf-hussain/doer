package com.project.doer.userReview;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.project.doer.R;
import com.project.doer.common.BaseFragment;
import com.project.doer.common.ConnectionDetector;
import com.project.doer.data.AppUtils;
import com.project.doer.userTask.UserTaskFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class UserReviewFragment extends BaseFragment implements UserReviewView {

    //ReviewAdapter reviewAdapter;
    @BindView(R.id.rv_user_task)
    RecyclerView rvUserTask;
    ConnectionDetector connectionDetector;
    SharedPreferences sharedPreferences;
    @BindView(R.id.ll_no_internet)
    LinearLayout llNoInternet;
    UserReviewPresenter userReviewPresenter;
    //@BindView(R.id.user_review_swipe_refresh)
    //SwipeRefreshLayout pullToRefresh;
    private static final String TAG = UserTaskFragment.class.getSimpleName();


    @Override
    public void showUserReview(ReviewModel reviewModel) {

        //reviewAdapter = new ReviewAdapter(taskModelList);
        // rvUserTask.setAdapter(reviewAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_task;
    }

    @Override
    protected void init() {
        connectionDetector = new ConnectionDetector(getContext());

        rvUserTask.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvUserTask.setLayoutManager(layoutManager);
        userReviewPresenter = new UserReviewImp(this,this.getActivity());
       // userReviewPresenter.loadUserReview();

    }


    @Override
    public void onResume() {
        super.onResume();
        checkpoint();
    }


    @Override
    public void checkpoint() {
        if (connectionDetector.isConnected()) {
            llNoInternet.setVisibility(View.GONE);

            //userReviewPresenter.loadUserReview();

        } else {
            llNoInternet.setVisibility(View.VISIBLE);
//            AppUtils.showSnackbar(this.getActivity(), pullToRefresh,
//                    getString(R.string.no_internet_connection));
        }
    }


    @OnClick({R.id.btn_retry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_retry:
                checkpoint();
                break;
        }
    }

}