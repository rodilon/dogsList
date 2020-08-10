package com.rodilon.dogs.domain.repository

import com.rodilon.dogs.domain.model.LoginData

interface ILoginRepository {

    suspend fun doLogin(email: String): LoginData
}
