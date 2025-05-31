package com.example.d4cfinalassignment.data.models.sendOtpModels

import com.example.d4cfinalassignment.data.models.sendOtpModels.Data

data class OtpResponse(
    val data: Data? = null,
    val message: String? = null,
    val statusCode: Int? = null,
    val success: Boolean? = null
)