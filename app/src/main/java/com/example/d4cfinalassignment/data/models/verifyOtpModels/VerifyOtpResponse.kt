package com.example.d4cfinalassignment.data.models.verifyOtpModels

data class VerifyOtpResponse(
    val `data`: DataX? = DataX(),
    val message: String? = "",
    val statusCode: Int? = 0,
    val success: Boolean? = false
)