package com.rodilon.dogs.data.repository

import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource
import com.rodilon.dogs.domain.repository.IDogsRepository

class DogsRepositoryImpl(
    private val dataSource: IDogsRemoteDataSource
) : IDogsRepository {
    override suspend fun fetchDogs(authorization: String, category: String) =
        dataSource.fetchDogs(authorization, category)
}