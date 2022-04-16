package com.example.sowlutions.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sowlutions.models.MyResponse
import com.example.sowlutions.repository.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _selectedImage = MutableLiveData<Int>()
    val selectedImage: LiveData<Int>
        get() = _selectedImage
    private lateinit var imageUri: Uri

    var myResponse: MutableLiveData<Response<MyResponse>> = MutableLiveData()

    fun getImageUri() : Uri {
        return this.imageUri;
    }
    fun setImageUri(uri: Uri){
        this.imageUri = uri
    }
    fun setSelectedImage(){
        _selectedImage.value = 1
    }

    fun getProducts(){
        viewModelScope.launch {
            val response = homeRepository.getProducts()
            myResponse.value = response
        }
    }
    fun getProductsWithKeyword(key: String){
        viewModelScope.launch {
            val response = homeRepository.getProductWithKeword(key)
            myResponse.value = response
        }
    }


}