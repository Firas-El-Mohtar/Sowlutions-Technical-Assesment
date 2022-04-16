package com.example.sowlutions.retrofit

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitInstance {
    val api: ProductApi by lazy{
       Retrofit.Builder()
           .baseUrl("https://app.markitworld.com/api/v2/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(ProductApi::class.java)
    }
}