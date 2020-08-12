package com.rodilon.dogs.data.datasource

import MockForUnitTest.AUTHORIZATION
import MockForUnitTest.CATEGORY
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.data.remote.api.Api
import com.rodilon.dogs.data.remote.source.implementation.DogsRemoteDataSourceImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsRemoteDataSourceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private val api: Api = mockk(relaxed = true)

    private lateinit var dogsRemoteDataSource: DogsRemoteDataSourceImpl

    @Before
    fun setup() {
        dogsRemoteDataSource = DogsRemoteDataSourceImpl(api)
    }

    @Test
    fun `fetch dogs test`() = runBlocking {
        dogsRemoteDataSource.fetchDogs(AUTHORIZATION, CATEGORY)

        coVerify(exactly = 1) {
            api.fetchDogs(AUTHORIZATION, CATEGORY)
        }
    }
}