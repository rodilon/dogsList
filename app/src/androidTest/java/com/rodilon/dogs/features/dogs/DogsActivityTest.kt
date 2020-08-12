package com.rodilon.dogs.features.dogs

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.rodilon.dogs.MockForAndroidTest.DOGS_DATA
import com.rodilon.dogs.MockForAndroidTest.TOKEN
import com.rodilon.dogs.R
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.di.IDomainModule
import com.rodilon.dogs.selectTabAtPosition
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DogsActivityTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(
        DogsActivity::class.java,
        true,
        false
    )

    @Before
    fun setup() {
        val domainModule = mockk<IDomainModule>()

        coEvery { domainModule.dogsUseCase.execute("", "") } returns DOGS_DATA

        every { domainModule.dogsUseCase.dispatcher } returns Dispatchers.Main

        ApplicationModules.domainModule = domainModule

        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = DogsActivity.getIntentActivity(targetContext, TOKEN)

        activityTestRule.launchActivity(intent)
    }

    @Test
    fun when_start_dogs_verify_tab() {
        onView(
            withId(R.id.tabLayout)
        ).perform(selectTabAtPosition(1))
    }

    @Test
    fun swipePage() {
        onView(withId(R.id.viewPager))
            .check(matches(isDisplayed()))
        onView(withId(R.id.viewPager))
            .perform(swipeLeft())
    }

    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(R.id.tabLayout))
            .perform(click())
            .check(matches(isDisplayed()))
    }

}