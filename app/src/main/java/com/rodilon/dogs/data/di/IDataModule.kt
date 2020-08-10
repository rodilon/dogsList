package com.rodilon.dogs.data.di

import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource
import com.rodilon.dogs.data.remote.source.interfaces.ILoginRemoteDataSource

interface IDataModule {

    val loginRemoteDataSource: ILoginRemoteDataSource
    val dogsRemoteDataSource: IDogsRemoteDataSource
}