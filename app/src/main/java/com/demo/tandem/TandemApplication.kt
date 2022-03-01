package com.demo.tandem

import android.app.Application
import com.demo.tandem.di.components.ApplicationComponent
import com.demo.tandem.di.components.DaggerApplicationComponent

open class TandemApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
    }
}