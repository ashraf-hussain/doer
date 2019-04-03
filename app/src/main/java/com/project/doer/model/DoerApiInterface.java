package com.project.doer.model;

import com.project.doer.GroupList;
import com.project.doer.adminTask.TaskModel;
import com.project.doer.group.GroupModel;
import com.project.doer.login.LoginModel;
import com.project.doer.signup.SignUpModel;
import com.project.doer.userNotice.EventModel;
import com.project.doer.userReview.ReviewModel;
import com.project.doer.userTask.UserTasklist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DoerApiInterface {

    @GET("groups")
    Call<GroupList> getGroups();


    @GET("events")
    Call<List<EventModel>> getEvents();

    @GET("tasks")
    Call<UserTasklist> getUserTask();

    @GET("reviews/task/{taskId}/user/{userId}")
    Call<ReviewModel> getUsersReviews(@Path("taskId") int taskId, @Path("userId") int userId);

    @POST("user/signup")
    Call<SignUpModel> actionUserSignup(@Body SignUpModel signUpModel);

    @POST("user/login")
    Call<LoginModel> actionLogin(@Body LoginModel loginModel);


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
    Call<UserTasklist> getAllGroupTask(@Path("groupId") int groupId);

    //Notice
    @POST("events")
    Call<EventModel> getEvents(@Body EventModel eventModel);

    //Review
    @POST("reviews")
    Call<ReviewModel> postReview(@Body ReviewModel reviewModel);


}
