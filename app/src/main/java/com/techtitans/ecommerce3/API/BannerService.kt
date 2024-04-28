package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.Banner
import retrofit2.Call
import retrofit2.http.GET

interface BannerService {
    @GET("banner/getAll")
    fun getBanners(): Call<List<Banner>>
}