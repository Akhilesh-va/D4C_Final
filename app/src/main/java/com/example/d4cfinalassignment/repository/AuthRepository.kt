package com.example.d4cfinalassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.d4cfinalassignment.data.api.OtpAPI
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpResponse
import com.example.d4cfinalassignment.presentation.screens.LoginScreen
import com.example.d4cfinalassignment.utils.NetworkResult
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject


class AuthRepository @Inject constructor(private val otpAPI: OtpAPI){


    private val _otpResult = MutableStateFlow<NetworkResult<OtpResponse>? >(null)
    val otpResult: StateFlow<NetworkResult<OtpResponse>?> = _otpResult

    suspend fun loginUser(otpRequest: OtpRequest) {
        _otpResult.emit(NetworkResult.Loading())
        try {
            val response = otpAPI.sendOtp(otpRequest)
            Log.d("TAG", response.body().toString())

            if (response.isSuccessful && response.body() != null) {
                _otpResult.emit(NetworkResult.Success(response.body()!!))
            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _otpResult.emit(NetworkResult.Error(errorObj.getString("message") ))
            }
        } catch (e: Exception) {
            _otpResult.emit(NetworkResult.Error("Network call failed: ${e.message}"))
        }
    }

}