package com.example.d4cfinalassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpRequest
import com.example.d4cfinalassignment.repository.VerifyOtpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyOtpViewmodel @Inject constructor(private val verifyOtpRepository: VerifyOtpRepository): ViewModel() {
    val verifyOtpResult = verifyOtpRepository.verifyOtpResult
    fun verifyOtp(verifyOtpRequest: VerifyOtpRequest) {
        viewModelScope.launch {
           verifyOtpRepository.verifyOtp(verifyOtpRequest)
        }
    }
}