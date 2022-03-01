package com.demo.tandem.di.modules

import androidx.lifecycle.ViewModelProvider
import com.demo.tandem.di.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("UNUSED")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}