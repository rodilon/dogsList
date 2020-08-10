package com.rodilon.dogs.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(

    @SerializedName("user")
    @Expose
    val user: User
)

data class User(

    @SerializedName("_id")
    @Expose
    val _id: String,

    @SerializedName("email")
    @Expose
    val email: String,

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
    val __v: Int
)
