package com.example.d4cfinalassignment.data.models.TicketModels

import android.net.Uri

data class TicketRequest(
    val ticketType: String = "",
    val message: String = "",
    val imageUri: Uri? = null,
)
