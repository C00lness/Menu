package com.example.dodomainlayout.view

import android.app.Application
import com.example.dodomainlayout.data.AppContainer
import com.example.dodomainlayout.data.DefaultAppContainer

class MyApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}