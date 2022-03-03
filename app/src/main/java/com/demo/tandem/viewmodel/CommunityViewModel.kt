package com.demo.tandem.viewmodel

import androidx.lifecycle.MutableLiveData
import com.demo.data.repository.CommunityRepository
import com.demo.domain.CommunityMember
import javax.inject.Inject

class CommunityViewModel @Inject constructor(private val communityRepository: CommunityRepository) :
    BaseViewModel() {

    val getCommunityMembersSuccess = MutableLiveData<List<CommunityMember>>()

    fun getCommunityMembers() {
        executeUseCase {
            val useCaseResult = communityRepository.getCommunityMembers(1)
            getCommunityMembersSuccess.postValue(useCaseResult)
        }
    }
}