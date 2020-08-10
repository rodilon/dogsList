package com.rodilon.dogs.data.remote.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodilon.dogs.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private lateinit var api: Api

    fun init() {
        provideApi()
    }

    private fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    private fun provideApi() {
        api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGsonBuilder()))
            .build()
            .create(Api::class.java)
    }

    fun getApi() = api
}