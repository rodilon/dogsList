package com.rodilon.dogs.features.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.security.ProviderInstaller
import com.rodilon.dogs.R
import com.rodilon.dogs.util.UtilsExtensions.hideKeyboard
import com.rodilon.dogs.util.UtilsExtensions.isValidEmail
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.features.dogs.DogsActivity
import kotlinx.android.synthetic.main.activity_login.*

private const val ERROR_DIALOG_REQUEST_CODE = 1

class LoginActivity : AppCompatActivity(), ProviderInstaller.ProviderInstallListener {

    private var retryProviderInstall: Boolean = false

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(ApplicationModules.domainModule.loginUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ProviderInstaller.installIfNeededAsync(this, this)

        initListeners()
        subscribeObservers()
    }

    private fun initListeners() {
        buttonLogin.setOnClickListener {
            this.hideKeyboard()
            val validEmail = editTextEmailLogin.text.toString()

            if (validEmail.isValidEmail()) {
                viewModel.doLogin(validEmail)
            } else {
                textViewWarningEmail.visibility = View.VISIBLE
            }
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textViewWarningEmail.visibility = View.GONE
            }
        }

        editTextEmailLogin.addTextChangedListener(textWatcher)
    }

    private fun subscribeObservers() {
        viewModel.login.observe(this, Observer { resource ->
            when (resource) {

                Resource.Loading -> {
                    println("LOADING...")
                }
                is Resource.Success -> {
                    val token = resource.result.user.token
                    finish()
                    startActivity(DogsActivity.getIntentActivity(this, token))
                }
                is Resource.Error -> {
                    println("ERROR... " + resource.error.message)
                }
            }
        })
    }


    // region Check Provider
    /**
     * This method is only called if the provider is successfully updated
     * (or is already up-to-date).
     */
    override fun onProviderInstalled() {
        // Provider is up-to-date, app can make secure network calls.
    }

    /**
     * This method is called if updating fails; the error code indicates
     * whether the error is recoverable.
     */
    override fun onProviderInstallFailed(errorCode: Int, recoveryIntent: Intent) {
        GoogleApiAvailability.getInstance().apply {
            if (isUserResolvableError(errorCode)) {
                // Recoverable error. Show a dialog prompting the user to
                // install/update/enable Google Play services.
                showErrorDialogFragment(this@LoginActivity, errorCode, ERROR_DIALOG_REQUEST_CODE) {
                    // The user chose not to take the recovery action
                    onProviderInstallerNotAvailable()
                }
            } else {
                onProviderInstallerNotAvailable()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ERROR_DIALOG_REQUEST_CODE) {
            // Adding a fragment via GoogleApiAvailability.showErrorDialogFragment
            // before the instance state is restored throws an error. So instead,
            // set a flag here, which will cause the fragment to delay until
            // onPostResume.
            retryProviderInstall = true
        }
    }

    /**
     * On resume, check to see if we flagged that we need to reinstall the
     * provider.
     */
    override fun onPostResume() {
        super.onPostResume()
        if (retryProviderInstall) {
            // We can now safely retry installation.
            ProviderInstaller.installIfNeededAsync(this, this)
        }
        retryProviderInstall = false
    }

    private fun onProviderInstallerNotAvailable() {
        finish()
    }

    // endregion
}