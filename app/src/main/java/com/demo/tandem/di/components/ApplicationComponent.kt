package com.demo.tandem.di.components

import android.app.Application
import com.demo.tandem.di.modules.ApplicationModule
import com.demo.tandem.ui.tandem.TandemActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(tandemActivity: TandemActivity)
}