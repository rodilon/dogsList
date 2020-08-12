package com.rodilon.dogs.features.login

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import com.rodilon.dogs.MockForAndroidTest.DOGS_DATA
import com.rodilon.dogs.MockForAndroidTest.LOGIN_DATA
import com.rodilon.dogs.R
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.di.IDomainModule
import com.rodilon.dogs.util.Constants
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(
        LoginActivity::class.java,
        true,
        false
    )

    @Before
    fun setup() {
        val domainModule = mockk<IDomainModule>()

        coEvery { domainModule.loginUseCase.execute(any()) } returns LOGIN_DATA

        every { domainModule.loginUseCase.dispatcher } returns Dispatchers.Main

        coEvery { domainModule.dogsUseCase.execute(any(), any()) } returns DOGS_DATA

        every { domainModule.dogsUseCase.dispatcher } returns  Dispatchers.Main

        ApplicationModules.domainModule = domainModule

        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun when_start_login_validate_label() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.textViewLabelLogin),
                ViewMatchers.withText("informe um email válido")
            )
        )
    }

    @Test
    fun when_start_login_validate_text_hint() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.editTextEmailLogin),
                ViewMatchers.withHint("email@dominio.com")
            )
        )
    }

    @Test
    fun when_text_wrong_email_show_warning() {
        Espresso.onView(
            ViewMatchers.withId(R.id.editTextEmailLogin)
        ).perform(ViewActions.typeText("renan"))

        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.textViewWarningEmail),
                ViewMatchers.withText("email incorreto")
            )
        )
    }

    @Test
    fun when_start_login_validate_button_visibility() {
        Espresso.onView(
            ViewMatchers.withId(R.id.buttonLogin)
        )
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun when_start_login_validate_static_message_button() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.buttonLogin),
                ViewMatchers.withText("login")
            )
        )
    }

    @Test
    fun when_click_login_load_next_page() {
        Espresso.onView(
            ViewMatchers.withId(R.id.editTextEmailLogin)
        ).perform(ViewActions.typeText("renan@odilon.com"))

        Espresso.onView(
            ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click())

        Intents.intended(
            IntentMatchers.hasExtraWithKey(
                CoreMatchers.`is`(
                    Constants.TOKEN
                )
            )
        )
    }
}