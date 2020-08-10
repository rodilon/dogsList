package com.rodilon.dogs.domain.usecases

import com.rodilon.dogs.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LoginUseCase(
    val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: ILoginRepository
) {
    suspend fun execute(email: String) = repository.doLogin(email)
}
