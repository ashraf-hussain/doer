package com.project.doer.model;

import com.project.doer.GroupList;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.group.GroupModel;
import com.project.doer.login.LoginModel;
import com.project.doer.signup.SignUpModel;
import com.project.doer.userNotice.EventModel;
import com.project.doer.userReview.ReviewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DoerApiInterface {

    @GET("groups")
    Call<GroupList> getGroups();

    @POST("groups")
    Call<GroupModel> createGroup(@Body GroupModel groupModel);

    @GET("events")
    Call<List<EventModel>> getEvents();

    @POST("events")
    Call<EventModel> getEvents(@Body EventModel eventModel);

    @GET("tasks")
    Call<List<TaskModel>> getTask();

    @POST("tasks")
    Call<TaskModel> postTask(@Body TaskModel taskModel);

    @GET("reviews")
    Call<List<ReviewModel>> getReviews();

    @POST("reviews")
    Call<ReviewModel> postReview(@Body ReviewModel reviewModel);

    @POST("user/admin/signup")
    Call<SignUpModel> actionAdminSignup(@Body SignUpModel signUpModel);

    @POST("user/signup")
    Call<SignUpModel> actionUserSignup(@Body SignUpModel signUpModel);

    @POST("user/login")
    Call<LoginModel> actionLogin(@Body LoginModel loginModel);

}
