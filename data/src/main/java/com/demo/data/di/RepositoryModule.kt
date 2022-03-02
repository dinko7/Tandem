package com.demo.data.di

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.mapper.CommunityMemberMapper
import com.demo.data.repository.CommunityRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class, DatabaseModule::class, MapperModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCommunityRepository(
        tandemApi: TandemApi,
        communityMemberMapper: CommunityMemberMapper
    ) =
        CommunityRepository(tandemApi, communityMemberMapper)
}