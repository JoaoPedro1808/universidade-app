package com.example.universidadeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_API = "http://10.0.2.2:8080/"

    val api: UniversitarioAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversitarioAPI::class.java)
    }
}