package com.demo.data.api

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.api.response.CommunityMembersResponse
import com.demo.data.api.response.MemberResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TandemApiTest : BaseApiTest() {

    private lateinit var tandemApi: TandemApi

    @Before
    fun setup() {
        tandemApi = testRetrofit.create(TandemApi::class.java)
    }

    @Test
    fun shouldGetCommunityMembers() = runBlocking {
        // given
        val page = 0
        val expectedResponse = CommunityMembersResponse().apply {
            response = listOf(
                MemberResponse().apply { id = "1" },
                MemberResponse().apply { id = "2" },
            )
        }
        enqueueResponse(expectedResponse)

        // when
        val actualResponse = tandemApi.getCommunityMembers(page)

        // then
        assertTrue(takeRequest().path!!.contains("community_$page.json"))
        assertEquals(expectedResponse.response!!.size, actualResponse.response!!.size)
        assertEquals(expectedResponse.response!![0].id, actualResponse.response!![0].id)
        assertEquals(expectedResponse.response!![1].id, actualResponse.response!![1].id)
    }
}