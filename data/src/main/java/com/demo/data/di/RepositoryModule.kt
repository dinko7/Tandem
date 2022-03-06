package com.demo.data.di

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.dao.favorite.FavoriteMemberDao
import com.demo.data.mapper.CommunityMemberMapper
import com.demo.data.repository.CommunityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCommunityRepository(
        tandemApi: TandemApi,
        favoriteMemberDao: FavoriteMemberDao,
        communityMemberMapper: CommunityMemberMapper
    ) =
        CommunityRepository(tandemApi, favoriteMemberDao, communityMemberMapper)
}