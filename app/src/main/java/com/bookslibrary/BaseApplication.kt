package com.bookslibrary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Divya V on 15-07-2024.
 */
@HiltAndroidApp
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}