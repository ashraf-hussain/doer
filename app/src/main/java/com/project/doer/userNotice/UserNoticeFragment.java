package com.project.doer.userNotice;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.doer.R;
import com.project.doer.common.BaseFragment;
import com.project.doer.common.ConnectionDetector;
import com.project.doer.data.AppUtils;
import com.project.doer.userReview.UserReviewFragment;

public class UserNoticeFragment extends BaseFragment {

    // TasAdapter taskAdapter;
//    @BindView(R.id.rv_user_task_fragment)
//    RecyclerView rvRockFragment;
    ConnectionDetector connectionDetector;
    //    @BindView(R.id.ll_offline_mode)
//    LinearLayout tvOfflineMode;
//    // UserTaskPresenter userTaskPresenter;
//    @BindView(R.id.swipe_refresh)
   // SwipeRefreshLayout pullToRefresh;
    private static final String TAG = UserReviewFragment.class.getSimpleName();

//
//    @Override
//    public void showRecyclerView(List<MusicModel> musicModelList) {
//
//        musicAdapter = new MusicAdapter(musicModelList);
//        rvRockFragment.setAdapter(musicAdapter);
//    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_task_review;
    }

    @Override
    protected void init() {
        connectionDetector = new ConnectionDetector(getContext());

        //rvRockFragment.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //rvRockFragment.setLayoutManager(layoutManager);

        // userTaskPresenter = new UserTaskImp(this);
        // userTaskPresenter.loadUserTask();

    }

    //swipe to refresh
//    private void swipeToRefresh() {
//        if (!connectionDetector.isConnected()) {
//            // tvOfflineMode.setVisibility(View.VISIBLE);
//
//            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    AppUtils.showSnackbar(pullToRefresh, "No Internet Connection !");
//                    pullToRefresh.setRefreshing(false);
//                }
//            });
//
//        } else {
//            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    //  userTaskPresenter.loadUserTask();
//                    //   tvOfflineMode.setVisibility(View.GONE);
//                    pullToRefresh.setRefreshing(false);
//                    AppUtils.showSnackbar(pullToRefresh, "Data Refreshed");
//                }
//            });
//
//        }
//
//    }
//
//    private void checkpoint() {
//
//        if (!connectionDetector.isConnected()) {
//            AppUtils.showSnackbar(pullToRefresh, "No Internet Connection !");
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        checkpoint();
//        swipeToRefresh();
//    }

}