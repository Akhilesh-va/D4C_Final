package com.example.d4cfinalassignment.data.models.verifyOtpModels

data class VerifyOtpRequest(
    val phoneNumber: String,
    val countryCode: String,
    val otp: String

)