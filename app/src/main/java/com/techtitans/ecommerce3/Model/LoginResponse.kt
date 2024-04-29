package com.techtitans.ecommerce3.Model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
)