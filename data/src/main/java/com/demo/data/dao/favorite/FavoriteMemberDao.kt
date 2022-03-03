package com.demo.data.dao.favorite

import androidx.room.*

@Dao
interface FavoriteMemberDao {

    @Query("SELECT * FROM favoritemember")
    suspend fun getAll(): List<FavoriteMember>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favorites: List<FavoriteMember>)

    @Delete
    suspend fun delete(favoriteMember: FavoriteMember)
}