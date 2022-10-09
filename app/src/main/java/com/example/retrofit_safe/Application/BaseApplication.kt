package com.example.retrofit_safe.Application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}