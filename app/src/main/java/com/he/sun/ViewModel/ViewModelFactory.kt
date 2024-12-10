package com.he.sun.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockInfoViewModel::class.java)) {
            return StockInfoViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}
