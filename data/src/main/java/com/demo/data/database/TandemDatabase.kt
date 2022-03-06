package com.demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.data.dao.favorite.FavoriteMember
import com.demo.data.dao.favorite.FavoriteMemberDao

@Database(entities = [FavoriteMember::class], version = 1)
abstract class TandemDatabase : RoomDatabase() {
    abstract fun favoriteMemberDao(): FavoriteMemberDao
}