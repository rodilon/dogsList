package com.rodilon.dogs.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private val dogsRemoteDataSource = mockk<IDogsRemoteDataSource>(relaxed = true)

    private lateinit var repository: DogsRepositoryImpl

    @Before
    fun setup() {
        repository = DogsRepositoryImpl(dogsRemoteDataSource)
    }

    @Test
    fun `fetch dogs test`() = runBlocking {
        repository.fetchDogs("", "")

        coVerify(exactly = 1) {
            dogsRemoteDataSource.fetchDogs("", "")
        }
    }
}