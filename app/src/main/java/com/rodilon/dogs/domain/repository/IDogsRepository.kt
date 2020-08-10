package com.rodilon.dogs.domain.repository

import com.rodilon.dogs.domain.model.DogsData

interface IDogsRepository {

    fun fetchDogs(authorization: String, category: String): DogsData
}
