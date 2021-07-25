package com.ishwar_arcore.saveo.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val instance: Retrofit? = null
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    /**
     * This class will let only one instance will be there.
     * This method will return the retrofit object if it ->
     * already instantiated then this will return the same  ->
     * object which instantiated in first go.
     * **/

    fun getRetrofitInstance(): Retrofit? {
        return RetrofitClient.instance
            ?: Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/")
                .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}