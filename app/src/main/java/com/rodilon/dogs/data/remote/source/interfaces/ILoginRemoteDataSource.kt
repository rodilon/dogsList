package com.rodilon.dogs.data.remote.source.interfaces

import com.rodilon.dogs.domain.model.LoginData

interface ILoginRemoteDataSource {

    fun doLogin(email: String): LoginData
}
