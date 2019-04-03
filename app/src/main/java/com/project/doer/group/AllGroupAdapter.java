package com.project.doer.group;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.adminTask.AdminTaskListActivity;
import com.project.doer.data.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllGroupAdapter extends RecyclerView.Adapter<AllGroupAdapter.ViewHolder> {

    private Context context;
    private List<GroupModel> groupModelList;
    private int lastPosition = -1;


    public AllGroupAdapter(List<GroupModel> userTaskList) {
        this.groupModelList = userTaskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_all_groups, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);

        final GroupModel groupModel = groupModelList.get(position);
        holder.tvGroupName.setText(groupModel.getGroupName());
        holder.tvGroupStartDate.setText(groupModel.getStartDate());
        holder.tvGroupPlatform.setText(groupModel.getPlatform());
        holder.cvAdapterAllGroupMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AdminTaskListActivity.class);
                i.putExtra(AppConstants.ASSIGN_TASK, groupModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupModelList == null ? 0 : groupModelList.size();

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_group_name)
        TextView tvGroupName;
        @BindView(R.id.tv_group_start_date)
        TextView tvGroupStartDate;
        @BindView(R.id.tv_group_platform)
        TextView tvGroupPlatform;
        @BindView(R.id.cv_adapter_all_group_main)
        CardView cvAdapterAllGroupMain;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}