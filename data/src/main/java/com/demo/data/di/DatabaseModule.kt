package com.demo.data.di

import android.app.Application
import androidx.room.Room
import com.demo.data.database.TandemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val databaseName = "tandem-db"

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) =
        Room.databaseBuilder(application, TandemDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideFavoriteMemberDao(tandemDatabase: TandemDatabase) =
        tandemDatabase.favoriteMemberDao()
}