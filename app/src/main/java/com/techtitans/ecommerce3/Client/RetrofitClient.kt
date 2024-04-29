package com.techtitans.ecommerce3.Client

import com.techtitans.ecommerce3.API.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    public const val BASE_URL = "http://93.95.26.208:8080/api/"


    private val retrofit by lazy {

        Retrofit.Builder()

            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())

            .build()

    }


    fun getUserApi(): UserService {

        return retrofit.create(UserService::class.java)

    }

}
