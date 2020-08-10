package com.rodilon.dogs.domain.di

import com.rodilon.dogs.domain.usecases.DogsUseCase
import com.rodilon.dogs.domain.usecases.LoginUseCase

interface IDomainModule {
    val loginUseCase: LoginUseCase
    val dogsUseCase: DogsUseCase
}