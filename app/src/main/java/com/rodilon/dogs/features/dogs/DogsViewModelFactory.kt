package com.rodilon.dogs.features.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodilon.dogs.domain.usecases.DogsUseCase

class DogsViewModelFactory(
    private val useCase: DogsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = DogsViewModel(useCase) as T
}