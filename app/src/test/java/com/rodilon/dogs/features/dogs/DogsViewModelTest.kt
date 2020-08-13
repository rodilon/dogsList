package com.rodilon.dogs.features.dogs

import com.rodilon.dogs.mock.MockForUnitTest.AUTHORIZATION
import com.rodilon.dogs.mock.MockForUnitTest.CATEGORY
import com.rodilon.dogs.mock.MockForUnitTest.DOGS_DATA
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.rodilon.dogs.util.MainCoroutineScopeRule
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.domain.repository.IDogsRepository
import com.rodilon.dogs.domain.usecases.DogsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsViewModelTest {

    private lateinit var subject: DogsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private fun initSubject() {

        val repository = mockk<IDogsRepository>()
        coEvery { repository.fetchDogs(AUTHORIZATION, CATEGORY) } returns DOGS_DATA

        val useCase = DogsUseCase(Dispatchers.Main, repository)

        subject = DogsViewModel(useCase)

    }

    @Test
    fun `when fetch dogs result is not null`() = runBlocking {
        initSubject()
        subject.fetchDogs(AUTHORIZATION, CATEGORY)

        Truth.assertThat(subject.dogsLiveData.value).isNotNull()
    }

    @Test
    fun `when fetch dogs result exception`() = runBlocking {
        val repository = mockk<IDogsRepository>()
        coEvery { repository.fetchDogs(AUTHORIZATION, CATEGORY) } throws Exception("Retornou erro")

        val useCase = DogsUseCase(Dispatchers.Main, repository)

        subject = DogsViewModel(useCase)

        subject.fetchDogs(AUTHORIZATION, CATEGORY)

        Truth.assertThat(subject.dogsLiveData.value).isInstanceOf(Resource.Error::class.java)
    }
}