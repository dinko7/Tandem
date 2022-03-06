package com.demo.data.mapper

import com.demo.data.api.response.CommunityMembersResponse
import com.demo.data.api.response.MemberResponse
import com.demo.domain.CommunityMember
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Test

class CommunityMemberMapperTest {

    @Test
    fun shouldMapCommunityMembersResponseToCommunityMembers() {
        // given
        val input = CommunityMembersResponse().apply {
            response = listOf(
                mockMemberResponse("1", "Ivan"),
                mockMemberResponse("2", "Luka"),
            )
        }

        // when
        val output = CommunityMemberMapper().mapCommunityMemberResponseToCommunityMembers(input)

        // then
        with(input.response!!) {
            assertEquals(size, output!!.size)
            assertCorrectMemberOutput(get(0), output[0])
            assertCorrectMemberOutput(get(1), output[1])
        }
    }

    private fun mockMemberResponse(id: String, name: String) = MemberResponse().apply {
        this.id = id
        firstName = name
        topic = "What's something not many people know about you?"
        pictureUrl = "https://tandem2019.web.app/img/pic1.png"
        natives = listOf("de", "ja", "it")
        learns = listOf("en", "pt")
        referenceCnt = 0
    }

    private fun assertCorrectMemberOutput(
        inputMember: MemberResponse,
        outputMember: CommunityMember
    ) {
        assertEquals(inputMember.id, outputMember.id)
        assertEquals(inputMember.firstName, outputMember.firstName)
        assertEquals(inputMember.topic, outputMember.topic)
        assertEquals(inputMember.natives, outputMember.natives)
        assertEquals(inputMember.learns, outputMember.learns)
        assertEquals(inputMember.referenceCnt, outputMember.referenceCnt)
        assertFalse(outputMember.isFavorite!!)
    }
}