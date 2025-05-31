package com.example.d4cfinalassignment.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.d4cfinalassignment.data.api.OtpAPI
import com.example.d4cfinalassignment.utils.NetworkResult
import com.example.d4cfinalassignment.utils.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val otpAPI: OtpAPI,
    private val tokenManager: TokenManager,
    @ApplicationContext private val context: Context
) {
    private val _ticketResult = MutableStateFlow<NetworkResult<Unit>?>(null)
    val ticketResult: StateFlow<NetworkResult<Unit>?> = _ticketResult

    suspend fun raiseTicket(ticketData: String, message: String, imageUri: Uri?) {
        _ticketResult.emit(NetworkResult.Loading())

        try {
            val token = tokenManager.getAccessToken()

            val ticketTypePart = ticketData.toRequestBody("text/plain".toMediaTypeOrNull())
            val messagePart = message.toRequestBody("text/plain".toMediaTypeOrNull())

            val imagePart = imageUri?.let { uri ->
                val file = uriToFile(uri)
                val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("files", file.name, reqFile)
            }

            val response = otpAPI.raiseTicket(
               "Bearer $token",
             ticketTypePart,
             messagePart,
               imagePart
            )

            if (response.isSuccessful) {
                _ticketResult.emit(NetworkResult.Success(Unit))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = JSONObject(errorBody ?: "").optString("message", "Something went wrong")
                _ticketResult.emit(NetworkResult.Error(errorMessage))
            }

        } catch (e: Exception) {
            Log.e("TicketRepository", "Error: ${e.message}")
            _ticketResult.emit(NetworkResult.Error("Failed to raise ticket: ${e.message}"))
        }
    }

    private fun uriToFile(uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_upload_file.jpg")
        file.outputStream().use { inputStream?.copyTo(it) }
        return file
    }
}
