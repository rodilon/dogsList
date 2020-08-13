package com.rodilon.dogs.domain.usecases

import com.rodilon.dogs.mock.MockForUnitTest.EMAIL
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.util.MainCoroutineScopeRule
import com.rodilon.dogs.domain.repository.ILoginRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUseCaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `execute usecase login test`() = runBlocking {
        val repository = mockk<ILoginRepository>(relaxed = true)

        LoginUseCase(repository = repository).execute(EMAIL)

        coVerify(exactly = 1) {
            repository.doLogin(EMAIL)
        }
    }
}