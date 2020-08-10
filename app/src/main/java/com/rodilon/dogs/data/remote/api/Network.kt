package com.rodilon.dogs.data.remote.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodilon.dogs.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private lateinit var okHttpBuilder: OkHttpClient.Builder
    private lateinit var api: Api

    fun init() {
        provideOkHttpBuilder()
        provideApi()
    }

    private fun provideOkHttpBuilder() {

        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val request = chain.request()
            request.newBuilder().addHeader("Content-Type:", "application/x-www-form-urlencoded").build()
            chain.proceed(request)
        }


        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        okHttpBuilder = builder
    }

    private fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    private fun provideApi() {
        api = Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGsonBuilder()))
            .build()
            .create(Api::class.java)
    }

    fun getApi() = api
}