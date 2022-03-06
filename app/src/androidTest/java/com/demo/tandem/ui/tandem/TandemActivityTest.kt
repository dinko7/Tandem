package com.demo.tandem.ui.tandem

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.demo.domain.CommunityMember
import com.demo.tandem.R
import com.demo.tandem.ui.base.BaseActivityTest
import com.demo.tandem.viewmodel.CommunityViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@HiltAndroidTest
class TandemActivityTest : BaseActivityTest() {

    override val activityClass = TandemActivity::class.java

    private val getCommunityMembersSuccess = MutableLiveData<List<CommunityMember>>()

    @Mock
    @BindValue
    lateinit var viewModel: CommunityViewModel

    @Before
    fun setUp() {
        whenever(viewModel.getCommunityMembersSuccess).thenReturn(getCommunityMembersSuccess)
    }

    @Test
    fun shouldGetCommunityMembersWhenLaunched() {
        // when
        launchActivity()

        // then
        verify(viewModel, times(1)).getCommunityMembers(1)
    }

    @Test
    fun shouldDisplayCommunityMembers() {
        // given
        val memberOne = mockMember("1", "Ivan")
        val memberTwo = mockMember("2", "Luka")
        launchActivity()

        // when
        runOnUiThread { getCommunityMembersSuccess.postValue(listOf(memberOne, memberTwo)) }

        // then
        onView(withText(memberOne.firstName)).check(matches(isDisplayed()))
        onView(withText(memberTwo.firstName)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldToggleFavoriteMember() {
        // given
        val member = mockMember("1", "Ivan").apply { isFavorite = false }
        launchActivity()
        runOnUiThread { getCommunityMembersSuccess.postValue(listOf(member)) }

        // when
        onView(withText(member.firstName)).perform(click())

        // then
        verify(viewModel, times(1)).toggleFavorite(member)
    }

    @Test
    @Ignore("Unfortunately, combination of scrollToPosition and swipeUp doesn't trigger onScrollListener on RecyclerView")
    fun shouldLoadMoreCommunityMembersWhenListIsScrolledToTheEnd() {
        // given
        val numberOfMembers = 10
        val members = List(numberOfMembers) { mockMember("$it", "$it") }
        launchActivity()
        runOnUiThread { getCommunityMembersSuccess.postValue(members) }

        // when
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                numberOfMembers - 1
            )
        )
        onView(withText("${numberOfMembers - 1}")).perform(swipeUp())
        
        // then
        verify(viewModel, times(2)).getCommunityMembers(any())
    }

    private fun mockMember(id: String, name: String) =
        CommunityMember().apply {
            this.id = id
            firstName = name
        }

}