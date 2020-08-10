package com.rodilon.dogs.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodilon.dogs.domain.usecases.LoginUseCase

class LoginViewModelFactory(
    private val useCase: LoginUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = LoginViewModel(
        useCase
    ) as T
}