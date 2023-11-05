package com.example.dodomainlayout.network.model

import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {

    @GET("categories.php")
    suspend fun getData(): CategoryArray
}