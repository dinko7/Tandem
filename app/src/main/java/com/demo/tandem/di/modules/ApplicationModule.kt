package com.demo.tandem.di.modules

import com.demo.data.di.RepositoryModule
import dagger.Module

@Module(includes = [ViewModelModule::class, RepositoryModule::class])
abstract class ApplicationModule