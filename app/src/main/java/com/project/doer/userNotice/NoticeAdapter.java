package com.project.doer.userNotice;

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
import com.project.doer.data.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private static final String TAG = NoticeAdapter.class.getName();


    private Context context;
    private List<EventModel> eventModelList;
    private int lastPosition = -1;


    public NoticeAdapter(List<EventModel> userTaskList) {
        this.eventModelList = userTaskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_user_notice, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);

        final EventModel eventModel = eventModelList.get(position);
        holder.tvEventTitle.setText(eventModel.getTitle());
        holder.tvEventDescription.setText(eventModel.getDescription());

            holder.cvUserEventAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, UserNoticeDetailActivity.class);
                    i.putExtra(AppConstants.NOTICE_DETAIL, eventModel);
                    context.startActivity(i);
                }
            });

    }

    @Override
    public int getItemCount() {
        return eventModelList == null ? 0 : eventModelList.size();

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_event_title)
        TextView tvEventTitle;
        @BindView(R.id.tv_event_description)
        TextView tvEventDescription;
        @BindView(R.id.cv_user_event_adapter)
        CardView cvUserEventAdapter;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
