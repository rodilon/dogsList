package com.rodilon.dogs.data.repository

import com.rodilon.dogs.domain.repository.IDogsRepository
import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource

class DogsRepositoryImpl(
    private val dataSource: IDogsRemoteDataSource
) : IDogsRepository {
    override fun fetchDogs(authorization: String, category: String) = dataSource.fetchDogs(authorization, category)
}