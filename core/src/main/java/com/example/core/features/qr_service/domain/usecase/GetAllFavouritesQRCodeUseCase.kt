package com.example.core.features.qr_service.domain.usecase

import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import javax.inject.Inject

class GetAllFavouritesQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: SaveQRCodeRepositoryContract
) {
    suspend fun execute(): List<QrCodeModel> {
        return qrCodeRepository.getFavoriteQRCodes()
    }
}