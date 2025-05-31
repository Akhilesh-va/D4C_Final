package com.example.d4cfinalassignment.data.models.TicketModels

import android.net.Uri

data class TicketRequest(
    val ticketType: String = "67ab787870baa5efe5404d63",
    val message: String = "",
    val imageUri: Uri? = null,
)
