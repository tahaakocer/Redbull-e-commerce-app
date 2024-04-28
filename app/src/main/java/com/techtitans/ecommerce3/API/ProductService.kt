package com.techtitans.ecommerce3.API

import com.techtitans.ecommerce3.Model.Category
import com.techtitans.ecommerce3.Model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("product/getAll")
    fun getProducts(): Call<List<Product>>
}