package com.demo.data.repository

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.data.preferences.PreferenceHandler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setupTest() {
        PreferenceHandler.init(RuntimeEnvironment.application)
    }

    @After
    fun tearDown() {
        PreferenceHandler.clearAll()
    }
}