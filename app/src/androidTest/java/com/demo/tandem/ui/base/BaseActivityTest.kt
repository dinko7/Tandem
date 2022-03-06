package com.demo.tandem.ui.base

import android.app.Activity
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
abstract class BaseActivityTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 3)
    var rule: MockitoRule = MockitoJUnit.rule()

    private lateinit var activityScenario: ActivityScenario<out Activity>
    protected abstract val activityClass: Class<out Activity>
    protected lateinit var activityInstance: Activity
        private set

    protected fun launchActivity(intent: Intent = Intent()) {
        activityScenario = ActivityScenario.launch(activityClass, intent.extras)
        activityScenario.onActivity { activityInstance = it }
    }

    fun runOnUiThread(action: () -> Unit) {
        activityInstance.runOnUiThread(action)
    }

    @After
    fun tearDown() {
        activityScenario.close()
    }
}
