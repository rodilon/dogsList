package com.rodilon.dogs.data.di

import com.rodilon.dogs.data.remote.api.Network
import com.rodilon.dogs.data.remote.source.implementation.DogsRemoteDataSourceImpl
import com.rodilon.dogs.data.remote.source.implementation.LoginRemoteDataSourceImpl
import com.rodilon.dogs.data.remote.source.interfaces.IDogsRemoteDataSource
import com.rodilon.dogs.data.remote.source.interfaces.ILoginRemoteDataSource

class DataModuleImpl : IDataModule {
    override val loginRemoteDataSource: ILoginRemoteDataSource by lazy {
        LoginRemoteDataSourceImpl(
            Network.getApi()
        )
    }
    override val dogsRemoteDataSource: IDogsRemoteDataSource by lazy {
        DogsRemoteDataSourceImpl(
            Network.getApi()
        )
    }
}