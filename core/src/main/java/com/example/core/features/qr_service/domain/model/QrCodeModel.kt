package com.example.core.features.qr_service.domain.model

data class QrCodeModel(
    val id: Int = 0,
    val qrCode: String,
    val isFavorite: Boolean = true
)