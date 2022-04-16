package com.example.sowlutions.repository

import com.example.sowlutions.models.MyResponse
import com.example.sowlutions.retrofit.RetrofitInstance
import retrofit2.Response

class HomeRepository {
    suspend fun getProducts(): Response<MyResponse>{
        return RetrofitInstance.api.getProducts()
    }
    suspend fun getProductWithKeword(key: String): Response<MyResponse>{
        return RetrofitInstance.api.getProductWithKeyword(key)
    }

}