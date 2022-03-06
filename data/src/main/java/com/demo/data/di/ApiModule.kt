package com.demo.data.di

import com.demo.data.api.endpoint.TandemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {
    @Provides
    @Singleton
    fun provideTandemApi(retrofit: Retrofit): TandemApi = retrofit.create(TandemApi::class.java)
}