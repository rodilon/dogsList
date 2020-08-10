package com.rodilon.dogs.domain

import java.lang.Exception

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val result: T) : Resource<T>()
    data class Error<out T>(val error: Exception) : Resource<T>()

}
