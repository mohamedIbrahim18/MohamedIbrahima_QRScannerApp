package com.example.core.features.qr_service.domain.usecase

import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import javax.inject.Inject

class SetFavouriteQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: SaveQRCodeRepositoryContract
) {

    suspend operator fun invoke(qrID: Int) {
        qrCodeRepository.setFavoriteQRCode(qrID)
    }
}