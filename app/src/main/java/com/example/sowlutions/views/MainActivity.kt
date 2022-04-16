package com.example.sowlutions.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sowlutions.R
import com.example.sowlutions.adapters.ProductAdapter
import com.example.sowlutions.databinding.ActivityMainBinding
import com.example.sowlutions.retrofit.RetrofitInstance
import com.example.sowlutions.viewmodel.HomeViewModel
import com.example.sowlutions.viewmodel.HomeViewModelFactory
import retrofit2.HttpException
import java.io.IOException
import androidx.appcompat.widget.SearchView


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    private val homeviewModel by viewModels<HomeViewModel>{
        HomeViewModelFactory()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()


//        lifecycleScope.launchWhenCreated {
//            binding.progressBar.isVisible = true
//            val response = try {
//                RetrofitInstance.api.getProducts()
//            } catch(e: IOException) {
//                Log.e(TAG, "IOException, you might not have internet connection")
//                binding.progressBar.isVisible = false
//                return@launchWhenCreated
//            } catch (e: HttpException) {
//                Log.e(TAG, "HttpException, unexpected response")
//                binding.progressBar.isVisible = false
//                return@launchWhenCreated
//            }
//            if(response.isSuccessful && response.body() != null) {
//                productAdapter.products = response.body()!!.data.products
//            } else {
//                Log.e(TAG, "Response not successful")
//            }
//            binding.progressBar.isVisible = false
//        }
        binding.progressBar.isVisible = true
//        homeviewModel.getProductsWithKeyword("water")
        homeviewModel.getProducts()
        homeviewModel.myResponse.observe(this, Observer {
            if(homeviewModel.myResponse.value!!.body() != null) {
                Log.e(TAG, "Response successful")
                productAdapter.products = homeviewModel.myResponse.value!!.body()!!.data.products
            }
        })
        binding.progressBar.isVisible = false


        homeviewModel.selectedImage.observe(this@MainActivity, Observer {
            val dialog = ProductImage(homeviewModel.getImageUri())
            Log.e(TAG, homeviewModel.getImageUri().toString())
            dialog.show(supportFragmentManager, "Large Image")
        })

    }

    override fun onResume() {
        super.onResume()
        homeviewModel.myResponse.observe(this, Observer {
            if(homeviewModel.myResponse.value?.body()?.data?.products != null) {
                productAdapter.products = homeviewModel.myResponse.value!!.body()!!.data.products
                productAdapter.notifyDataSetChanged()
                Log.e(TAG, "Response successful")
            }
        })
    }


    private fun setupRecyclerView() = binding.recyclerView.apply {
        productAdapter = ProductAdapter(homeviewModel)
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
        if(query != null){
            searchAPI(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchAPI(query)
        }
        return true
    }

    private fun searchAPI(query: String){
        homeviewModel.getProductsWithKeyword(query)
        productAdapter.notifyDataSetChanged()
    }

}



