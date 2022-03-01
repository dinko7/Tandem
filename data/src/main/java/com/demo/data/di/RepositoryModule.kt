package com.demo.data.di

import com.demo.data.di.ApiModule
import com.demo.data.di.DatabaseModule
import dagger.Module

@Module(includes = [ApiModule::class, DatabaseModule::class])
internal class RepositoryModule {
}