package com.rodilon.dogs.data.remote.source.implementation

import com.rodilon.dogs.domain.model.DogsData
import com.rodilon.dogs.data.remote.api.Api
import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource

class DogsRemoteDataSourceImpl(
    private val api: Api
) : IDogsRemoteDataSource {
    override suspend fun fetchDogs(
        authorization: String,
        category: String
    ): DogsData = api.fetchDogs(authorization, category)
}