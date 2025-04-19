package com.example.core.features.qr_service.data.repository

import com.example.core.features.qr_service.data.local.Dao.QRCodeDao
import com.example.core.features.qr_service.data.local.mapper.toDomain
import com.example.core.features.qr_service.data.local.mapper.toEntity
import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import javax.inject.Inject

class SaveQRCodeRepositoryImpl @Inject constructor(
    private val qrCodeDao: QRCodeDao
) : SaveQRCodeRepositoryContract {

    override suspend fun saveQRCode(qrCode: QrCodeModel) {
        val qrCodeEntity = qrCode.toEntity()
        qrCodeDao.insertQRCode(qrCodeEntity)
    }

    override suspend fun getAllQRCodes(): List<QrCodeModel> {
        val qrCodeEntities = qrCodeDao.getAllQRCodes()
        return qrCodeEntities.map { it.toDomain() }
    }

    override suspend fun getFavoriteQRCodes(): List<QrCodeModel> {
        val favoriteEntities = qrCodeDao.getFavoriteQRCodes()
        return favoriteEntities.map { it.toDomain() }
    }

    override suspend fun setFavoriteQRCode(id: Int) {
        qrCodeDao.setFavorite(id)
    }

    override suspend fun unSetFavoriteQRCode(id: Int) {
        qrCodeDao.unSetFavorite(id)
    }
}