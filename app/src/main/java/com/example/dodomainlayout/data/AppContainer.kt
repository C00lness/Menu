package com.example.dodomainlayout.data

import android.content.Context
import com.example.dodomainlayout.DataBase.dbCategoryManager
import com.example.dodomainlayout.network.model.RetrofitService
import com.example.dodomainlayout.view.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val repository: Repository
}

class DefaultAppContainer (context: Context) : AppContainer {
    //private val BASE_URL = "https://g8fhlgccof.execute-api.eu-central-1.amazonaws.com/chapter2/"
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val dbCategoryManager = dbCategoryManager(context)
    private val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override val repository: Repository by lazy {
        MyRepository(retrofitService, dbCategoryManager)
    }
}