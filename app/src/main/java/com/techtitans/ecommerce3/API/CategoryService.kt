package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.Banner
import com.techtitans.ecommerce3.Model.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("category/getAll")
    fun getCategories(): Call<List<Category>>
}