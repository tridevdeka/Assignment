package com.tridev.assignment

import android.app.Application
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        private lateinit var mInstance: BaseApplication

        @Synchronized
        fun getInstance(): BaseApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance=this
    }
}