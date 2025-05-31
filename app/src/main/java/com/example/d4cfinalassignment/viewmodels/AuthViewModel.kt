package com.example.d4cfinalassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    val otpResult = authRepository.otpResult

    fun loginUser(otpRequest: OtpRequest) {
        viewModelScope.launch {

            authRepository.loginUser(otpRequest)
        }

    }
}