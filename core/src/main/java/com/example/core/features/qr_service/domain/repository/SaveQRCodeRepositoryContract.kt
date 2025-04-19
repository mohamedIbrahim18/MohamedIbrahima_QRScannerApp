package com.example.core.features.qr_service.domain.repository

import com.example.core.features.qr_service.domain.model.QrCodeModel

interface SaveQRCodeRepositoryContract {
    suspend fun saveQRCode(qrCode: QrCodeModel)
    suspend fun getAllQRCodes(): List<QrCodeModel>
    suspend fun getFavoriteQRCodes(): List<QrCodeModel>
    suspend fun setFavoriteQRCode(id: Int)
    suspend fun unSetFavoriteQRCode(id: Int)
}