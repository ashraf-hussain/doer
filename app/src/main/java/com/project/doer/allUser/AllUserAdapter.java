package com.project.doer.allUser;

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

class AllUserAdapter extends RecyclerView.Adapter<AllUserAdapter.ViewHolder> {

    private static final String TAG = AllUserAdapter.class.getName();

    private Context context;
    private List<AllUsersModel> allUsersModelList;
    private int lastPosition = -1;


    public AllUserAdapter(List<AllUsersModel> userTaskList) {
        this.allUsersModelList = userTaskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_all_users, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);

        final AllUsersModel allUsersModel = allUsersModelList.get(position);

        holder.tvUserName.setText(allUsersModel.getFirstName() + " " + allUsersModel.getLastName());
        holder.cvAllUserAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AdminUserFeebackActivity.class);
                i.putExtra(AppConstants.All_USER, allUsersModel);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return allUsersModelList == null ? 0 : allUsersModelList.size();

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.cv_all_user_adapter)
        CardView cvAllUserAdapter;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
