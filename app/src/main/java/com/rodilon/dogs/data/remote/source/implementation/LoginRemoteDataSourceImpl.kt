package com.rodilon.dogs.data.remote.source.implementation

import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.data.remote.api.Api
import com.rodilon.dogs.data.remote.source.interfaces.ILoginRemoteDataSource

class LoginRemoteDataSourceImpl(
    private val api: Api
) : ILoginRemoteDataSource {
    override suspend fun doLogin(email: String): LoginData = api.doLogin(email)
}