package com.rodilon.dogs.features.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.hawk.Hawk
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.util.Constants.TOKEN

private const val EMPTY = ""
private const val DELAY = 2000L

class SplashScreenViewModel : ViewModel() {

    private val _tokenMutableLiveData = MutableLiveData<Resource<String>>()
    val tokenLiveData: LiveData<Resource<String>> = _tokenMutableLiveData

    fun verifyTokenSaved() {
        val handle = Handler()
        handle.postDelayed({
            showLoading()
        }, DELAY)
    }

    private fun showLoading() {
        if (Hawk.contains(TOKEN)) {
            val token = Hawk.get<String>(TOKEN)
            _tokenMutableLiveData.value = Resource.Success(token)
        } else {
            _tokenMutableLiveData.value = Resource.Success(EMPTY)
        }
    }
}