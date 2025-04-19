package com.example.core.features.qr_service.domain.usecase

import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import javax.inject.Inject

class UnSetFavouriteQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: SaveQRCodeRepositoryContract
) {

    suspend operator fun invoke(qrId: Int) {
        qrCodeRepository.unSetFavoriteQRCode(qrId)
    }
}