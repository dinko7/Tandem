package com.demo.tandem.di

import com.demo.data.repository.CommunityRepository
import com.demo.tandem.viewmodel.CommunityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {
    @Provides
    fun provideCommunityViewModel(repository: CommunityRepository) = CommunityViewModel(repository)
}