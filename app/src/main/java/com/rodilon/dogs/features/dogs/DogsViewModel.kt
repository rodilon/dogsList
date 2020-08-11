package com.rodilon.dogs.features.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.domain.model.DogsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class DogsViewModel : ViewModel() {

    private val useCase = ApplicationModules.domainModule.dogsUseCase

    private val _dogsMutableLiveData = MutableLiveData<Resource<DogsData>>()
    val dogsLiveData: LiveData<Resource<DogsData>> = _dogsMutableLiveData

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(useCase.dispatcher + viewModelJob)

    fun fetchDogs(authorization: String, category: String) {
        ioScope.launch {
            _dogsMutableLiveData.postValue(Resource.Loading)

            try {
                val responseLogin = useCase.execute(authorization, category)
                _dogsMutableLiveData.postValue(
                    Resource.Success(
                        responseLogin
                    )
                )

            } catch (e: IOException) {
                _dogsMutableLiveData.postValue(Resource.Error(e))
            }
        }
    }
}