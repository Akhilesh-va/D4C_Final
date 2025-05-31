package com.example.d4cfinalassignment.data.models.productModels

data class ProductData(
    val _id: String? = null,
    val images: List<Image?>? = null,
    val inCartQuantity: Int? = null,
    val isBestSeller: Boolean? = null,
    val isInCart: Boolean? = null,
    val isLiked: Boolean? = null,
    val maxBuyQuantity: Int? = null,
    val mrp: Int? = null,
    val price: Int? = null,
    val productId: String? = null,
    val quantity: Int? = null,
    val reviewsCount: Int? = null,
    val reviewsRating: Int? = null,
    val stockStatus: String? = null,
    val subTitle: String? = null,
    val subTitle2: String? = null,
    val title: String? = null
)