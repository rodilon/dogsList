package com.rodilon.dogs.domain.usecases

import MockForUnitTest.AUTHORIZATION
import MockForUnitTest.CATEGORY
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.domain.repository.IDogsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsUseCaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `execute usecase fetchDogs test`() = runBlocking {
        val repository = mockk<IDogsRepository>(relaxed = true)

        DogsUseCase(repository = repository).execute(AUTHORIZATION, CATEGORY)

        coVerify(exactly = 1) {
            repository.fetchDogs(AUTHORIZATION, CATEGORY)
        }
    }
}