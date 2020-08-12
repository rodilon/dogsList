package com.rodilon.dogs.features.login

import MockForUnitTest.EMAIL
import MockForUnitTest.LOGIN_DATA
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.domain.model.User
import com.rodilon.dogs.domain.repository.ILoginRepository
import com.rodilon.dogs.domain.usecases.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var subject: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private fun initSubject() {

        val repository = mockk<ILoginRepository>()
        coEvery { repository.doLogin(EMAIL) } returns LOGIN_DATA

        val useCase = LoginUseCase(Dispatchers.Main, repository)

        subject = LoginViewModel(useCase)

    }

    @Test
    fun `when do login result is not null`() = runBlocking {
        initSubject()
        subject.doLogin(EMAIL)

        assertThat(subject.login.value).isNotNull()
    }

    @Test
    fun `when do login result exception`() = runBlocking {
        val repository = mockk<ILoginRepository>()
        coEvery { repository.doLogin(EMAIL) } throws Exception("Retornou erro")

        val useCase = LoginUseCase(Dispatchers.Main, repository)

        subject = LoginViewModel(useCase)

        subject.doLogin(EMAIL)

        assertThat(subject.login.value).isInstanceOf(Resource.Error::class.java)
    }
}