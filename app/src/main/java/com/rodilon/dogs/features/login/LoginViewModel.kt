package com.rodilon.dogs.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.domain.usecases.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    private val useCase: LoginUseCase
) : ViewModel() {

    private val _login = MutableLiveData<Resource<LoginData>>()
    val login: LiveData<Resource<LoginData>> = _login

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(useCase.dispatcher + viewModelJob)

    private lateinit var responseLogin: LoginData

    fun doLogin(email: String) {
        ioScope.launch {
            _login.postValue(Resource.Loading)

            try {
                responseLogin = useCase.execute(email)
                _login.postValue(
                    Resource.Success(
                        responseLogin
                    )
                )

            } catch (e: Exception) {
                _login.postValue(Resource.Error(e))
            }
        }
    }
}