package com.demo.tandem.viewmodel

import com.demo.data.repository.CommunityRepository
import com.demo.domain.CommunityMember
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CommunityViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var repository: CommunityRepository

    @InjectMocks
    private lateinit var viewModel: CommunityViewModel

    @Test
    fun shouldGetCommunityMembersByPage() = runBlocking {
        // given
        val expectedResult = listOf(
            CommunityMember().apply {
                id = "1"
            },
            CommunityMember().apply {
                id = "2"
            },
        )
        whenever(repository.getCommunityMembers(any())).thenReturn(expectedResult)

        // when
        viewModel.getCommunityMembers(0)

        // then
        verify(repository, times(1)).getCommunityMembers(0)
        assertEquals(expectedResult, viewModel.getCommunityMembersSuccess.value)
    }

    @Test
    fun shouldToggleFavoriteIfPreviousValueWasTrue() = runBlocking {
        // given
        val member = CommunityMember().apply {
            id = "1"
            isFavorite = true
        }
        whenever(repository.addToFavorites(member)).thenReturn(Unit)

        // when
        viewModel.toggleFavorite(member)

        // then
        verify(repository, times(1)).deleteFromFavorites(member)
    }

    @Test
    fun shouldToggleFavoriteIfPreviousValueWasFalse() = runBlocking {
        // given
        val member = CommunityMember().apply {
            id = "1"
            isFavorite = false
        }
        whenever(repository.addToFavorites(member)).thenReturn(Unit)

        // when
        viewModel.toggleFavorite(member)

        // then
        verify(repository, times(1)).addToFavorites(member)
    }
}