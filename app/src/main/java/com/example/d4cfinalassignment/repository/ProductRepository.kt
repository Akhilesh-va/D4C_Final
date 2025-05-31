package com.example.d4cfinalassignment.repository

import android.util.Log
import com.example.d4cfinalassignment.data.api.OtpAPI
import com.example.d4cfinalassignment.data.models.productModels.Product
import com.example.d4cfinalassignment.utils.NetworkResult
import com.example.d4cfinalassignment.utils.TokenManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

class ProductRepository@Inject constructor(private val otpAPI: OtpAPI ,
    private val tokenManager: TokenManager)  {
    private val _productResult = MutableStateFlow<NetworkResult<Product>?>(null)
    val productResult: StateFlow<NetworkResult<Product>?> = _productResult

    suspend fun fetchProducts() {
        _productResult.emit(NetworkResult.Loading())
        val token = tokenManager.getAccessToken()
        try {
            val response = otpAPI.getProduct("Bearer $token")
            Log.d("pv", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                _productResult.emit(NetworkResult.Success(response.body()!!))
            } else {
                val errorObj = JSONObject(response.errorBody()?.charStream()?.readText() ?: "")
                _productResult.emit(NetworkResult.Error(errorObj.optString("message", "Something went wrong")))
            }
        } catch (e: Exception) {
            Log.e("ProductRepo", "Error fetching products", e)
            _productResult.emit(NetworkResult.Error("Network call failed: ${e.message}"))
        }

}}