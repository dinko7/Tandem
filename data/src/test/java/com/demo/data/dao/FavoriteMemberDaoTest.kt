package com.demo.data.dao

import com.demo.data.dao.favorite.FavoriteMember
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FavoriteMemberDaoTest : BaseDaoTest() {

    @Test
    fun shouldInsertFavoriteMembers() = runBlocking {
        // given
        val favoriteMemberOne = FavoriteMember().apply { id = "1" }
        val favoriteMemberTwo = FavoriteMember().apply { id = "2" }

        // when
        testDatabase.favoriteMemberDao().insertAll(listOf(favoriteMemberOne, favoriteMemberTwo))

        // then
        val favoritesFromDb = testDatabase.favoriteMemberDao().getAll()
        assertNotNull(favoritesFromDb.find { it.id == favoriteMemberOne.id })
        assertNotNull(favoritesFromDb.find { it.id == favoriteMemberTwo.id })
    }

    @Test
    fun shouldDeleteFavoriteMember() = runBlocking {
        // given
        val favoriteMember = FavoriteMember().apply { id = "1" }
        testDatabase.favoriteMemberDao().insertAll(listOf(favoriteMember))

        // when
        testDatabase.favoriteMemberDao().delete(favoriteMember)

        // then
        val favoritesFromDb = testDatabase.favoriteMemberDao().getAll()
        assertNull(favoritesFromDb.find { it.id == favoriteMember.id })
    }
}