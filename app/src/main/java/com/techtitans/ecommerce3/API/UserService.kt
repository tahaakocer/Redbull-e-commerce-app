package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("user/getByEmail/{email}")

    fun getUserByEmail(@Path("email") email: String): Call<User>

}