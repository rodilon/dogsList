package com.rodilon.dogs.data.remote.api

import com.rodilon.dogs.domain.model.DogsData
import com.rodilon.dogs.domain.model.LoginData
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("/signup")
    fun doLogin(@Field("email") request: String): LoginData

    @GET("/feed")
    fun fetchDogs(
        @Header("Authorization") authorization: String,
        @Query("category") category: String
    ) : DogsData
}
