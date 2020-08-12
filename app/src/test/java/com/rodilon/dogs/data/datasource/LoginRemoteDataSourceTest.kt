package com.rodilon.dogs.data.datasource

import MockForUnitTest.EMAIL
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rodilon.dogs.MainCoroutineScopeRule
import com.rodilon.dogs.data.remote.api.Api
import com.rodilon.dogs.data.remote.source.implementation.LoginRemoteDataSourceImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginRemoteDataSourceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private val api: Api = mockk(relaxed = true)

    private lateinit var loginRemoteDataSource: LoginRemoteDataSourceImpl

    @Before
    fun setup() {
        loginRemoteDataSource = LoginRemoteDataSourceImpl(api)
    }

    @Test
    fun `do login test`() = runBlocking {
        loginRemoteDataSource.doLogin(EMAIL)

        coVerify(exactly = 1) {
            api.doLogin(EMAIL)
        }
    }
}