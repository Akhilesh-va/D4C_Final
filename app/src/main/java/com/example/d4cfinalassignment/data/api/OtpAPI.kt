package com.example.d4cfinalassignment.data.api

import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpResponse
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpRequest
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpResponse
import com.example.d4cfinalassignment.data.models.productModels.Product
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.annotation.processing.Generated

interface OtpAPI {
    @POST("api/auth/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): Response<OtpResponse>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>

    @GET("api/product")
    suspend fun getProduct(
        @Header("Authorization") authHeader: String
    ): Response<Product>
}