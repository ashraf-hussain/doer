package com.project.doer.userTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.project.doer.R;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.data.AppConstants;
import com.project.doer.userReview.UserReviewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static final String TAG = TaskAdapter.class.getName();


     private Context context;
     private List<TaskModel> taskModelList;
     private int lastPosition = -1;


    public TaskAdapter(List<TaskModel> userTaskList) {
        this.taskModelList = userTaskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_user_task, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);

        final TaskModel taskModel = taskModelList.get(position);
        holder.tvTaskTitle.setText(taskModel.getTitle());
        holder.tvTaskDescription.setText(taskModel.getDescription());
        holder.tvTaskDeadline.setText(taskModel.getDeadline());

        if (taskModel.getReviewed()) {
            holder.tvReviewed.setVisibility(View.VISIBLE);
            holder.cvUserTaskAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, UserReviewActivity.class);
                    i.putExtra(AppConstants.TASK_DETAIL, taskModel);
                    Log.d(TAG, "onClick: "+taskModel.getTitle());
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return taskModelList == null ? 0 : taskModelList.size();

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_task_title)
        TextView tvTaskTitle;
        @BindView(R.id.tv_reviewed)
        TextView tvReviewed;
        @BindView(R.id.tv_task_description)
        TextView tvTaskDescription;
        @BindView(R.id.tv_task_deadline)
        TextView tvTaskDeadline;
        @BindView(R.id.cv_user_task_adapter)
        CardView cvUserTaskAdapter;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
