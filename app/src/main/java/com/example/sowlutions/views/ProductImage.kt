package com.example.sowlutions.views

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.sowlutions.R


class ProductImage(private val imageUri: Uri) : DialogFragment() {

    //private lateinit var binding: FragmentProductImageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding = FragmentProductImageBinding.bind(view)
        //binding.largeImage.let {
        //    val imgUri = imageUri.buildUpon().scheme("https").build()
        //    binding.largeImage.load(imgUri)
        //}

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_image, container, false)
        val image = view.findViewById<ImageView>(R.id.largeImage)
        image.let {
            val imgUri = imageUri.buildUpon().scheme("https").build()
            image.load(imgUri)
        }
        return view
    }


    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.window?.setLayout(width, height)
        }
    }
}