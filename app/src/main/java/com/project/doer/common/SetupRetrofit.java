package com.project.doer.common;

import com.project.doer.data.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SetupRetrofit {

    String BaseURL = "https://doer-puzan.herokuapp.com/api/v1/";

    public Retrofit getRetrofit() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
    }


//    public Retrofit getRetrofitWithAuthHeader(final String auth) {
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                Request request = original.newBuilder()
//                        .header("Authorization", AppConstants.TOKEN_TYPE + " " + auth)
//                        .header("api_key", AppConstants.API_TOKEN)
//                        .method(original.method(), original.body())
//                        .build();
//                Response response = chain.proceed(request);
//
//                return response;
//            }
//        });
//
//        OkHttpClient client = httpClient.build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        return retrofit;
//    }

}
