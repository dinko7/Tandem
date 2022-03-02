package com.demo.data.di

import com.demo.data.mapper.CommunityMemberMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class MapperModule {
    @Provides
    @Singleton
    fun provideCommunityMemberMapper() = CommunityMemberMapper()
}