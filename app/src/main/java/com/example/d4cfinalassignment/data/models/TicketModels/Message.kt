package com.example.d4cfinalassignment.data.models.TicketModels

data class Message(
    val _id: String? = null,
    val `by`: String? = null,
    val createdAt: String? = null,
    val image: List<Image?>? = null,
    val message: String? = null,
    val readBy: List<Any?>? = null,
    val userId: String? = null
)