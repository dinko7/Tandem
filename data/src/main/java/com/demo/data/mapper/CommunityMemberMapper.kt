package com.demo.data.mapper

import com.demo.data.api.response.CommunityMembersResponse
import com.demo.data.api.response.MemberResponse
import com.demo.domain.CommunityMember

class CommunityMemberMapper {
    fun mapCommunityMemberResponseToCommunityMembers(response: CommunityMembersResponse) =
        response.response?.map {
            CommunityMember().apply {
                id = it.id
                topic = it.topic
                firstName = it.firstName
                pictureUrl = it.pictureUrl
                natives = it.natives
                learns = it.learns
                referenceCnt = it.referenceCnt
                isFavorite = false
            }
        }
}