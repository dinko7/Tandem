package com.demo.tandem.viewmodel

import androidx.lifecycle.MutableLiveData
import com.demo.data.repository.CommunityRepository
import com.demo.domain.CommunityMember
import javax.inject.Inject

class CommunityViewModel @Inject constructor(private val communityRepository: CommunityRepository) :
    BaseViewModel() {

    val getCommunityMembersSuccess = MutableLiveData<List<CommunityMember>>()

    fun getCommunityMembers(page: Int) {
        executeUseCase {
            val useCaseResult = communityRepository.getCommunityMembers(page)
            getCommunityMembersSuccess.postValue(useCaseResult)
        }
    }

    fun toggleFavorite(communityMember: CommunityMember) {
        executeUseCase {
            when (communityMember.isFavorite) {
                true -> communityRepository.deleteFromFavorites(communityMember)
                false -> communityRepository.addToFavorites(communityMember)
                null -> {}
            }
        }
    }
}