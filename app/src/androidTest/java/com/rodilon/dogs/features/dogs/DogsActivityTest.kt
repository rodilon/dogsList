package com.rodilon.dogs.features.dogs

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.rodilon.dogs.features.mock.MockForAndroidTest.TOKEN
import com.rodilon.dogs.features.mock.MockForAndroidTest.getDogsData
import com.rodilon.dogs.R
import com.rodilon.dogs.features.util.RecyclerViewMatcher
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.di.IDomainModule
import com.rodilon.dogs.features.util.selectTabAtPosition
import com.rodilon.dogs.util.Constants.HUSKY
import com.rodilon.dogs.util.Constants.LABRADOR
import com.rodilon.dogs.util.Constants.PUG
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class DogsActivityTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(
        DogsActivity::class.java,
        true,
        false
    )

    private fun launchActivity(category: String) {
        val domainModule = mockk<IDomainModule>()

        coEvery { domainModule.dogsUseCase.execute(any(), any()) } returns getDogsData(category)

        every { domainModule.dogsUseCase.dispatcher } returns Dispatchers.Main

        ApplicationModules.domainModule = domainModule

        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = DogsActivity.getIntentActivity(targetContext, TOKEN)

        activityTestRule.launchActivity(intent)
    }

    private fun initFragmentDogs() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.dogs_container, DogsFragment.newInstance(TOKEN, LABRADOR), null)
            .commit()
    }

    @Test
    fun when_start_dogs_verify_tab() {
        launchActivity(PUG)
        Espresso.onView(
            ViewMatchers.withId(R.id.tabLayout)
        ).perform(selectTabAtPosition(2))
    }

    @Test
    fun validate_zoom_image() {
        launchActivity(LABRADOR)
        initFragmentDogs()

        Espresso.onView(
            withRecyclerView(R.id.recyclerViewDogs).atPosition(0)
        ).perform(ViewActions.click())

        Espresso.onView(
            ViewMatchers.withId(R.id.imageZoom)
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun validate_tabLayout_is_displayed() {
        launchActivity(HUSKY)
        Espresso.onView(ViewMatchers.withId(R.id.tabLayout))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}
