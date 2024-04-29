package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.Atlet
import com.techtitans.ecommerce3.Model.Banner
import retrofit2.Call
import retrofit2.http.GET

interface AtletService {
    @GET("atlet/getAll")
    fun getAtlets(): Call<List<Atlet>>
}