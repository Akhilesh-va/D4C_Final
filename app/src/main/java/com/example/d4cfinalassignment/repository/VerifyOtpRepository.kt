package com.example.d4cfinalassignment.repository

import android.util.Log
import com.example.d4cfinalassignment.data.api.OtpAPI
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpResponse
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpRequest
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpResponse
import com.example.d4cfinalassignment.utils.NetworkResult
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

class VerifyOtpRepository @Inject constructor(private val otpAPI: OtpAPI) {


    private val _verifyOtpResult = MutableStateFlow<NetworkResult<VerifyOtpResponse>? >(null)
    val verifyOtpResult: StateFlow<NetworkResult<VerifyOtpResponse>?> = _verifyOtpResult

    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest) {
        _verifyOtpResult.emit(NetworkResult.Loading())
        try {
            val response = otpAPI.verifyOtp(verifyOtpRequest)
            Log.d("TAG2", response.body().toString())

            if (response.isSuccessful && response.body() != null) {
                _verifyOtpResult.emit(NetworkResult.Success(response.body()!!))
            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _verifyOtpResult.emit(NetworkResult.Error(errorObj.getString("message") ))
            }
        } catch (e: Exception) {
            _verifyOtpResult.emit(NetworkResult.Error("Network call failed: ${e.message}"))
        }
    }
}