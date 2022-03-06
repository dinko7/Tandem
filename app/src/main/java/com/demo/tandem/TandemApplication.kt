package com.demo.tandem

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class TandemApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isDebugFlavor()) Timber.plant(Timber.DebugTree())
    }

    private fun isDebugFlavor() = BuildConfig.BUILD_TYPE == "debug"
}