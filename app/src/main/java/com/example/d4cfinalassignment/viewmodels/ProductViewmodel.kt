package com.example.d4cfinalassignment.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d4cfinalassignment.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductViewmodel@Inject constructor(private val productRepository: ProductRepository): ViewModel()  {
    val productResultView = productRepository.productResult

    fun fetchProducts() {
        viewModelScope.launch {
            productRepository.fetchProducts()
        }
        Log.d("ProductViewmodel", productResultView.toString())
    }

}