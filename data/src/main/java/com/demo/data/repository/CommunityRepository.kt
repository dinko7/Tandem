package com.demo.data.repository

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.mapper.CommunityMemberMapper
import com.demo.domain.CommunityMember
import javax.inject.Inject

class CommunityRepository @Inject constructor(
    private val tandemApi: TandemApi,
    private val communityMemberMapper: CommunityMemberMapper
) {

    suspend fun getCommunityMembers(page: Int): List<CommunityMember>? {
        val response = tandemApi.getCommunityMembers(page)
        return communityMemberMapper.mapCommunityMemberResponseToCommunityMembers(response)
    }

}