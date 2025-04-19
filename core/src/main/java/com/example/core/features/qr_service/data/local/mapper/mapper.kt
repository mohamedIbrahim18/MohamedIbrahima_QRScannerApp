package com.example.core.features.qr_service.data.local.mapper

import com.example.core.features.qr_service.data.local.model.QRCodeEntity
import com.example.core.features.qr_service.domain.model.QrCodeModel

fun QrCodeModel.toEntity(): QRCodeEntity {
    return QRCodeEntity(qrCode = this.qrCode, isFavorite = this.isFavorite)
}

fun QRCodeEntity.toDomain(): QrCodeModel {
    return QrCodeModel(id = this.id, qrCode = this.qrCode, isFavorite = this.isFavorite)
}
