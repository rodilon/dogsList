package com.rodilon.dogs.data.repository

import com.rodilon.dogs.domain.repository.ILoginRepository
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.data.remote.source.interfaces.ILoginRemoteDataSource

class LoginRepositoryImpl(
    private val dataSource: ILoginRemoteDataSource
) : ILoginRepository {
    override suspend fun doLogin(email: String): LoginData = dataSource.doLogin(email)
}