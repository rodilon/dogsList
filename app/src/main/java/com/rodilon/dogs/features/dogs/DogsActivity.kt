package com.rodilon.dogs.features.dogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodilon.dogs.Constants.TOKEN
import com.rodilon.dogs.R
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

        textView.text = token
    }
}