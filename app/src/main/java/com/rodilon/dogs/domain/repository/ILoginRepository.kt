package com.rodilon.dogs.domain.repository

import com.rodilon.dogs.domain.model.LoginData

interface ILoginRepository {

    fun doLogin(email: String): LoginData
}
