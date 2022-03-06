package com.demo.tandem.ui.tandem

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.demo.domain.CommunityMember
import com.demo.tandem.ui.base.BaseRecyclerViewAdapterTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class CommunityMembersRecyclerViewAdapterTest : BaseRecyclerViewAdapterTest() {

    private val adapter = CommunityMembersRecyclerViewAdapter()

    @Before
    fun setUp() {
        launchActivity()
        runOnUiThread { recyclerView.adapter = adapter }
    }

    @Test
    fun shouldDisplayCommunityMembers() {
        // given
        val memberOne = mockMember("1", "Ivan", "en", "it", 0)
        val memberTwo = mockMember("2", "Luka", "es", "ru", 1)

        // when
        runOnUiThread { adapter.appendAll(listOf(memberOne, memberTwo)) }

        // then
        onView(withText(memberOne.firstName)).check(matches(isDisplayed()))
        onView(withText(memberOne.natives!![0].uppercase())).check(matches(isDisplayed()))
        onView(withText(memberOne.learns!![0].uppercase())).check(matches(isDisplayed()))
        onView(withText(memberTwo.firstName)).check(matches(isDisplayed()))
        onView(withText(memberTwo.natives!![0].uppercase())).check(matches(isDisplayed()))
        onView(withText(memberTwo.learns!![0].uppercase())).check(matches(isDisplayed()))
        onView(withText("${memberTwo.referenceCnt}")).check(matches(isDisplayed()))
    }

    private fun mockMember(
        id: String,
        name: String,
        nativeLanguage: String,
        learnsLanguage: String,
        refCount: Int
    ) =
        CommunityMember().apply {
            this.id = id
            firstName = name
            natives = listOf(nativeLanguage)
            learns = listOf(learnsLanguage)
            referenceCnt = refCount
        }
}