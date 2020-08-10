package com.rodilon.dogs.domain.di

import com.rodilon.dogs.domain.repository.IDogsRepository
import com.rodilon.dogs.domain.repository.ILoginRepository
import com.rodilon.dogs.domain.usecases.DogsUseCase
import com.rodilon.dogs.domain.usecases.LoginUseCase

class DomainModuleImpl(
    private val loginRepository: ILoginRepository,
    private val dogsRepository: IDogsRepository
) : IDomainModule {
    override val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(repository = loginRepository)
    }
    override val dogsUseCase: DogsUseCase by lazy {
        DogsUseCase(repository = dogsRepository)
    }
}