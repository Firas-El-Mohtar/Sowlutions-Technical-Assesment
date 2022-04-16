package com.example.sowlutions.adapters

import android.app.Activity
import android.app.FragmentManager
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sowlutions.databinding.ProductItemViewBinding
import com.example.sowlutions.models.Product
import com.example.sowlutions.viewmodel.HomeViewModel
import com.example.sowlutions.views.MainActivity
import com.example.sowlutions.views.ProductImage

class ProductAdapter(private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ProductItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var products: List<Product>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            val product = products[position]
            productTitle.text = product.title
            descriptionText.text = product.description
            productThumbnail.let {
                val imgUri = product.images[0].thumbnail.toUri().buildUpon().scheme("https").build()
                productThumbnail.load(imgUri)
            }
            productPrice.text = product.price
            productCard.setOnClickListener{
                homeViewModel.setImageUri(product.images[0].large.toUri())
                homeViewModel.setSelectedImage()
            }

        }

    }

}