package com.example.core.features.domain.usecase

import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.UnSetFavouriteQRCodeUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UnSetFavouriteQRCodeUseCaseTest {

    private lateinit var unSetFavouriteQRCodeUseCase: UnSetFavouriteQRCodeUseCase
    private val qrCodeRepository: SaveQRCodeRepositoryContract = mockk(relaxed = true)

    @Before
    fun setUp() {
        unSetFavouriteQRCodeUseCase = UnSetFavouriteQRCodeUseCase(qrCodeRepository)
    }

    @Test
    fun `should call unSetFavoriteQRCode on repository with correct ID`() = runTest {
        val qrId = 13

        unSetFavouriteQRCodeUseCase.invoke(qrId)

        coVerify(exactly = 1) { qrCodeRepository.unSetFavoriteQRCode(qrId) }
    }
}
