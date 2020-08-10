package com.rodilon.dogs.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("userId")
    @Expose
    val userId: String,

    @SerializedName("token")
    @Expose
    val token: String,

    @SerializedName("createdAt")
    @Expose
    val createdAt: String,

    @SerializedName("updatedAt")
    @Expose
    val updatedAt: String,

    @SerializedName("__v")
    @Expose
    val v: Int
)
