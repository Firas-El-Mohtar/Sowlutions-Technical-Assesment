package com.example.sowlutions.retrofit

import com.example.sowlutions.models.MyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @Headers(value = ["Authorization: 446a6828200604377695aa034cf57e36", "StoreID: 1"])
    @GET("user/products")
    suspend fun getProducts(): Response<MyResponse>
    @Headers(value = ["Authorization: 446a6828200604377695aa034cf57e36", "StoreID: 1"])
    @GET("user/products")
    suspend fun getProductWithKeyword(@Query("keyword") keyword: String): Response<MyResponse>


}