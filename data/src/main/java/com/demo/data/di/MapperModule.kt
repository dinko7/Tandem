package com.demo.data.di

import com.demo.data.mapper.CommunityMemberMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class MapperModule {
    @Provides
    @Singleton
    fun provideCommunityMemberMapper() = CommunityMemberMapper()
}