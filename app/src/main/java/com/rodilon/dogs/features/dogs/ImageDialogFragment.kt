package com.rodilon.dogs.features.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rodilon.dogs.R
import com.rodilon.dogs.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_dialog.*

class ImageDialogFragment : DialogFragment() {

    lateinit var url: String

    companion object {
        var TAG = ImageDialogFragment::class.java.name
        fun newInstance(url: String): ImageDialogFragment {
            val fragment = ImageDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(Constants.URL, url)
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_image_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(Constants.URL) }?.apply {
            url = getString(Constants.URL)!!
        }

        loadFullScreenImage()

        imageZoom.maximumScale = 5.0F

        close_image.setOnClickListener {
            dismiss()
        }
    }

    private fun loadFullScreenImage() {
        Picasso.get()
            .load(url)
            .into(imageZoom)
    }
}