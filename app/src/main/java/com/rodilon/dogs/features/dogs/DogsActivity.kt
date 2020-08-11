package com.rodilon.dogs.features.dogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import com.rodilon.dogs.R
import com.rodilon.dogs.features.login.LoginActivity
import com.rodilon.dogs.util.Constants.TOKEN
import kotlinx.android.synthetic.main.activity_dogs.*

class DogsActivity : AppCompatActivity() {

    private lateinit var token: String

    companion object {
        fun getIntentActivity(
            context: Context,
            token: String
        ): Intent = Intent(context, DogsActivity::class.java).apply {
            putExtra(TOKEN, token)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)

        intent.extras?.let {
            token = it.getString(TOKEN)!!
        }

        saveToken()

        supportFragmentManager.beginTransaction()
            .replace(R.id.dogs_container, ViewPagerFragment.newInstance(token), null)
            .commit()

        setupToolbar()

    }

    private fun setupToolbar() {
        with(toolbar) {

            title = getString(R.string.title_toolbar)

            setNavigationOnClickListener {
                finish()
            }

            inflateMenu(R.menu.logout_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.logout) {
                    if (Hawk.contains(TOKEN)) {
                        finish()
                        startActivity(Intent(context, LoginActivity::class.java))
                        Hawk.deleteAll()
                    }
                }
                true
            }
        }
    }

    private fun saveToken() {
        if (!Hawk.contains(TOKEN)) {
            Hawk.put(TOKEN, token)
        }
    }
}