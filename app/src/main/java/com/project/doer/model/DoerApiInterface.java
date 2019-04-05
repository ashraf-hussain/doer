package com.project.doer.model;

import com.project.doer.GroupList;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.allUser.AllUserDataList;
import com.project.doer.group.GroupModel;
import com.project.doer.login.LoginModel;
import com.project.doer.login.LoginModelBody;
import com.project.doer.signup.SignUpModel;
import com.project.doer.userNotice.EventModel;
import com.project.doer.userNotice.UserEventList;
import com.project.doer.userReview.ReviewModel;
import com.project.doer.userTask.UserDatalist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DoerApiInterface {

    @GET("groups")
    Call<GroupList> getGroups();

    @GET("events")
    Call<UserEventList> getUserEvents();

    @GET("tasks")
    Call<UserDatalist> getUserTask();

    @GET("reviews/task/{taskId}/user/{userId}")
    Call<ReviewModel> getUsersReviews(@Path("taskId") int taskId, @Path("userId") int userId);

    @POST("user/signup")
    Call<SignUpModel> actionUserSignUp(@Body SignUpModel signUpModel);

    @POST("user/login")
    Call<LoginModel> actionLogin( @Body LoginModel loginModel);


    //Admin

    //Group
    @POST("groups")
    Call<GroupModel> createGroup(@Body GroupModel groupModel);


    @POST("user/admin/signup")
    Call<SignUpModel> actionAdminSignup(@Body SignUpModel signUpModel);

    //Task
    @POST("tasks")
    Call<TaskModel> postTask(@Body TaskModel taskModel);

    @GET("tasks/group/{groupId}")
    Call<UserDatalist> getGroupTask(@Path("groupId") int groupId);

    //All Users
    @GET("user/all")
    Call<AllUserDataList> getAllUsers();

    //Notice
    @POST("events")
    Call<EventModel> postUserEvents(@Body EventModel eventModel);

    //Review
    @POST("reviews")
    Call<ReviewModel> postReview(@Body ReviewModel reviewModel);


}
