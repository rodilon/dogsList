package com.rodilon.dogs.domain.usecases

import com.rodilon.dogs.domain.repository.IDogsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DogsUseCase(
    val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: IDogsRepository
) {

    suspend fun execute(authorization: String, category: String) =
        repository.fetchDogs(authorization, category)
}
