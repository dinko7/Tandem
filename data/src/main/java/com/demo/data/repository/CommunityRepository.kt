package com.demo.data.repository

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.dao.favorite.FavoriteMember
import com.demo.data.dao.favorite.FavoriteMemberDao
import com.demo.data.mapper.CommunityMemberMapper
import com.demo.domain.CommunityMember
import javax.inject.Inject

class CommunityRepository @Inject constructor(
    private val tandemApi: TandemApi,
    private val favoriteMemberDao: FavoriteMemberDao,
    private val communityMemberMapper: CommunityMemberMapper
) {

    suspend fun getCommunityMembers(page: Int): List<CommunityMember>? {
        val apiResponse = tandemApi.getCommunityMembers(page)
        val favoritesFromDatabase = favoriteMemberDao.getAll().map { it.id }.toSet()
        return communityMemberMapper.mapCommunityMemberResponseToCommunityMembers(apiResponse)
            ?.map {
                it.apply {
                    isFavorite = if (it.id != null) favoritesFromDatabase.contains(it.id) else false
                }
            }
    }

    suspend fun addToFavorites(member: CommunityMember) {
        favoriteMemberDao.insertAll(listOf(FavoriteMember().apply { id = member.id }))
    }

    suspend fun deleteFromFavorites(member: CommunityMember) {
        favoriteMemberDao.delete(FavoriteMember().apply { id = member.id })
    }
}