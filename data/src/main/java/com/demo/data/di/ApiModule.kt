package com.demo.data.di

import com.demo.data.api.endpoint.TandemApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
internal class ApiModule {
    @Provides
    @Singleton
    fun provideTandemApi(retrofit: Retrofit) = retrofit.create(TandemApi::class.java)
}