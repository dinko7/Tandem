package com.demo.data.repository

import com.demo.data.api.endpoint.TandemApi
import com.demo.data.api.response.CommunityMembersResponse
import com.demo.data.api.response.MemberResponse
import com.demo.data.dao.favorite.FavoriteMember
import com.demo.data.dao.favorite.FavoriteMemberDao
import com.demo.data.mapper.CommunityMemberMapper
import com.demo.domain.CommunityMember
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.*

class CommunityRepositoryTest : BaseRepositoryTest() {

    @Mock
    private lateinit var tandemApi: TandemApi

    @Mock
    private lateinit var favoriteMemberDao: FavoriteMemberDao

    @Mock
    private lateinit var mapper: CommunityMemberMapper

    @InjectMocks
    private lateinit var repository: CommunityRepository

    @Test
    fun shouldGetCommunityMembers() = runBlocking {
        // given
        val apiResponse = CommunityMembersResponse().apply {
            response = listOf(
                MemberResponse().apply {
                    id = "1"
                    firstName = "Ivan"
                },
                MemberResponse().apply {
                    id = "2"
                    firstName = "Luka"
                },
            )
        }
        val daoResult = listOf(FavoriteMember().apply { id = "1" })
        val mapperResult = generateCommunityMembersFromApiResponse(apiResponse)
        whenever(tandemApi.getCommunityMembers(any())).thenReturn(apiResponse)
        whenever(favoriteMemberDao.getAll()).thenReturn(daoResult)
        whenever(mapper.mapCommunityMemberResponseToCommunityMembers(apiResponse)).thenReturn(
            mapperResult
        )

        // when
        val communityMembers = repository.getCommunityMembers(0)

        // then
        verify(tandemApi, times(1)).getCommunityMembers(0)
        verify(favoriteMemberDao, times(1)).getAll()
        verify(mapper, times(1)).mapCommunityMemberResponseToCommunityMembers(apiResponse)
        assertEquals(mapperResult!!.size, communityMembers!!.size)
        assertEqualMembers(mapperResult[0], communityMembers[0])
        assertTrue(communityMembers[0].isFavorite!!)
        assertEqualMembers(mapperResult[1], communityMembers[1])
        assertFalse(communityMembers[1].isFavorite!!)
    }

    @Test
    fun shouldAddMemberToFavorites(): Unit = runBlocking {
        // given
        val member = CommunityMember().apply { id = "id" }

        // when
        repository.addToFavorites(member)

        // then
        argumentCaptor<List<FavoriteMember>> {
            verify(favoriteMemberDao, times(1)).insertAll(capture())
            assertEquals(1, firstValue.size)
            assertEquals(member.id, firstValue[0].id)
        }
    }

    @Test
    fun shouldDeleteMemberFromFavorites(): Unit = runBlocking {
        // given
        val member = CommunityMember().apply { id = "id" }

        // when
        repository.deleteFromFavorites(member)

        // then
        argumentCaptor<FavoriteMember> {
            verify(favoriteMemberDao, times(1)).delete(capture())
            assertEquals(member.id, firstValue.id)
        }
    }

    private fun generateCommunityMembersFromApiResponse(apiResponse: CommunityMembersResponse) =
        apiResponse.response?.map {
            CommunityMember().apply {
                id = it.id
                firstName = it.firstName
                isFavorite = false
            }
        }

    private fun assertEqualMembers(inputMember: CommunityMember, outputMember: CommunityMember) {
        assertEquals(inputMember.id, outputMember.id)
        assertEquals(inputMember.firstName, outputMember.firstName)
    }
}