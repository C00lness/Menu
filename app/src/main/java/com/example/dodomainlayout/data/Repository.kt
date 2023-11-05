package com.example.dodomainlayout.data

import android.util.Log
import com.example.dodomainlayout.DataBase.dbCategoryManager
import com.example.dodomainlayout.network.model.Category
import com.example.dodomainlayout.network.model.CategoryArray
import com.example.dodomainlayout.network.model.Pizza
import com.example.dodomainlayout.network.model.RetrofitService

interface Repository {
    suspend fun getData() : CategoryArray
    fun getDataFromDb() : CategoryArray
}

class MyRepository(private val service: RetrofitService, private val dbCategoryManager: dbCategoryManager) : Repository{
    override suspend fun getData(): CategoryArray = service.getData()
    override fun getDataFromDb(): CategoryArray{
        dbCategoryManager.OpenDb()
        return dbCategoryManager.ReadData()
    }
}