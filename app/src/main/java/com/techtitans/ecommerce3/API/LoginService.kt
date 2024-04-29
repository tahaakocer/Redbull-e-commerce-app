package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST

interface LoginService {

    // Method to handle user login
    @FormUrlEncoded
    @POST("user/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>  // Use LoginResponse class to parse the response

    @FormUrlEncoded
    @POST("user/register")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>
}