package com.example.secondtrainep;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//public interface ApiService {
//    @POST("signup")
//    Call<ApiResponse> signUp(@Body User user);
//
//    @POST("signin")
//    Call<ApiResponse> signIn(@Body User user);
//}


public interface ApiService {
    @POST("signup") // Make sure the endpoint matches your API
    Call<ApiResponse> signUp(@Body User user);

    // Define other API calls as needed


    @POST("signIn")
    Call<ApiResponse> signIn(@Body User user);
}

