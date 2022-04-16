package com.example.sowlutions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sowlutions.repository.HomeRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                homeRepository = HomeRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}