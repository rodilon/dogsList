package com.rodilon.dogs.data.remote.source.interfaces

import com.rodilon.dogs.domain.model.DogsData

interface IDogsRemoteDataSource {

    suspend fun fetchDogs(authorization: String, category: String): DogsData
}
