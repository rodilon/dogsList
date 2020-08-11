package com.rodilon.dogs.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rodilon.dogs.R
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.features.dogs.DogsActivity
import com.rodilon.dogs.features.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel.verifyTokenSaved()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.tokenLiveData.observe(this, Observer { resource ->
            when (resource) {

                Resource.Loading -> {
                    // do nothing
                }
                is Resource.Success -> {
                    finish()
                    if (resource.result.isEmpty()) {
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        startActivity(DogsActivity.getIntentActivity(this, resource.result))
                    }
                }
                is Resource.Error -> {
                    // do nothing
                }
            }
        })
    }
}