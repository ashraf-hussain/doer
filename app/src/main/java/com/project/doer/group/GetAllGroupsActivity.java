package com.project.doer.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.doer.CreateGroupActivity;
import com.project.doer.R;
import com.project.doer.common.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetAllGroupsActivity extends BaseActivity implements GetAllGroupsView {
    @BindView(R.id.rv_get_all_groups)
    RecyclerView rvGetAllGroups;
    GetAllGroupsPresenter getAllGroupsPresenter;
    @BindView(R.id.tb_title)
    TextView tbTitle;


    @Override
    protected int getLayout() {
        return R.layout.activity_all_groups;
    }

    @Override
    protected void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tbTitle.setText(R.string.all_groups);
        rvGetAllGroups.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvGetAllGroups.setLayoutManager(layoutManager);

        getAllGroupsPresenter = new GetAllGropusImp(this, this);
        getAllGroupsPresenter.loadAllGroups();

    }

    @Override
    public void viewAllGroups(List<GroupModel> groupModelList) {

        AllGroupAdapter allGroupAdapter = new AllGroupAdapter(groupModelList);
        rvGetAllGroups.setAdapter(allGroupAdapter);
    }

    @Override
    public void onGroupFoundSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_create_group)
    public void onViewClicked() {
        Intent i = new Intent(this, CreateGroupActivity.class);
        startActivity(i);
    }


    @OnClick(R.id.toolbar)
    public void onClicked() {
        onBackPressed();
    }
}

