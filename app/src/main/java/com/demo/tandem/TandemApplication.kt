package com.demo.tandem

import android.app.Application
import com.demo.tandem.di.components.ApplicationComponent
import com.demo.tandem.di.components.DaggerApplicationComponent
import timber.log.Timber

open class TandemApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        if (isDebugFlavor()) Timber.plant(Timber.DebugTree())
    }

    private fun isDebugFlavor() = BuildConfig.BUILD_TYPE == "debug"
}