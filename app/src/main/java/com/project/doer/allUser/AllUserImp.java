package com.project.doer.allUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.project.doer.common.SetupRetrofit;
import com.project.doer.data.AppConstants;
import com.project.doer.model.DoerApiInterface;
import com.project.doer.userTask.UserDatalist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class AllUserImp implements AllUserPresenter {

    private Context context;
    private AllUserView allUserView;
    private AllUserDataList allUserDataList;
    private List<AllUsersModel> allUsersModelList;

    public AllUserImp(Context context, AllUserView allUserView) {
        this.context = context;
        this.allUserView = allUserView;
    }

    @Override
    public void loadAllUsers() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.TOKEN_DATA, MODE_PRIVATE);

        String token = sharedPreferences.getString(AppConstants.TOKEN, "");

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofitWithAuthHeader(token);
        DoerApiInterface doerApiInterface = retrofit.create(DoerApiInterface.class);
        doerApiInterface.getAllUsers().enqueue(new Callback<AllUserDataList>() {
            private final String TAG = AllUserImp.class.getName();

            @Override
            public void onResponse(Call<AllUserDataList> call, Response<AllUserDataList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Toast.makeText(context, response.code() + "", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: " + response.raw().request().url());
                if (response.isSuccessful()) {

                    allUserDataList = response.body();
                    allUsersModelList = allUserDataList.getAllUsersModelList();
                    allUserView.showAllUsers(allUsersModelList);
                }
            }

            @Override
            public void onFailure(Call<AllUserDataList> call, Throwable t) {

            }
        });
    }
}
