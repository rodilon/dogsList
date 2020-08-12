package com.rodilon.dogs.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource
import com.rodilon.dogs.data.remote.source.interfaces.ILoginRemoteDataSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private val loginRemoteDataSource = mockk<ILoginRemoteDataSource>(relaxed = true)

    private lateinit var repository: LoginRepositoryImpl

    @Before
    fun setup() {
        repository = LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Test
    fun `do login test`() = runBlocking {
        repository.doLogin("")

        coVerify(exactly = 1) {
            loginRemoteDataSource.doLogin("")
        }
    }

}