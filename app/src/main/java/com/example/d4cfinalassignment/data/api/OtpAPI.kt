package com.example.d4cfinalassignment.data.api

import com.example.d4cfinalassignment.data.models.TicketModels.TicketReponse
import com.example.d4cfinalassignment.data.models.productModels.Product
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpRequest
import com.example.d4cfinalassignment.data.models.sendOtpModels.OtpResponse
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpRequest
import com.example.d4cfinalassignment.data.models.verifyOtpModels.VerifyOtpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OtpAPI {
    @POST("api/auth/send-otp")
    suspend fun sendOtp(@Body request: OtpRequest): Response<OtpResponse>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>

    @GET("api/product")
    suspend fun getProduct(
        @Header("Authorization") authHeader: String
    ): Response<Product>

    @Multipart
    @POST("api/ticket")
    suspend fun raiseTicket(
        @Header("Authorization") authHeader: String,
        @Part("ticketType") ticketType: RequestBody,
        @Part("message") message: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<TicketReponse>
}