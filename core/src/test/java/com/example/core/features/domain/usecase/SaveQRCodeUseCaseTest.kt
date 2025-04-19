package com.example.core.features.domain.usecase

import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.SaveQRCodeUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveQRCodeUseCaseTest {

    private lateinit var saveQRCodeUseCase: SaveQRCodeUseCase
    private val qrCodeRepository: SaveQRCodeRepositoryContract = mockk(relaxed = true)

    @Before
    fun setUp() {
        saveQRCodeUseCase = SaveQRCodeUseCase(qrCodeRepository)
    }

    @Test
    fun `should call saveQRCode on repository with correct QR code`() = runTest {
        val qrCode = QrCodeModel(id = 1, qrCode = "https://example.com", isFavorite = false)

        saveQRCodeUseCase.invoke(qrCode)

        coVerify(exactly = 1) { qrCodeRepository.saveQRCode(qrCode) }
    }
}
