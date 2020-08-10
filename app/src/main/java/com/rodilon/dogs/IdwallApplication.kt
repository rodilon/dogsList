package com.rodilon.dogs

import android.app.Application
import com.rodilon.dogs.data.di.DataModuleImpl
import com.rodilon.dogs.data.remote.api.Network
import com.rodilon.dogs.data.repository.DogsRepositoryImpl
import com.rodilon.dogs.data.repository.LoginRepositoryImpl
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.di.DomainModuleImpl

class IdwallApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Network.init()

        ApplicationModules.apply {
            val dataModule = DataModuleImpl()

            domainModule = DomainModuleImpl(
                LoginRepositoryImpl(dataModule.loginRemoteDataSource),
                DogsRepositoryImpl(dataModule.dogsRemoteDataSource)
            )
        }
    }
}