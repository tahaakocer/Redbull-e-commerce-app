package com.techtitans.ecommerce3.Model

import com.google.gson.annotations.SerializedName

data class Category(
    val id: Int,
    val title: String,
    @SerializedName("image")
    val picUrl: String
)
