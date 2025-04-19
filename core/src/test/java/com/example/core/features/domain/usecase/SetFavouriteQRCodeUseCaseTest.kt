package com.example.core.features.domain.usecase

import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.SetFavouriteQRCodeUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SetFavouriteQRCodeUseCaseTest {

    private lateinit var setFavouriteQRCodeUseCase: SetFavouriteQRCodeUseCase
    private val qrCodeRepository: SaveQRCodeRepositoryContract = mockk(relaxed = true)

    @Before
    fun setUp() {
        setFavouriteQRCodeUseCase = SetFavouriteQRCodeUseCase(qrCodeRepository)
    }

    @Test
    fun `should call setFavoriteQRCode on repository with correct ID`() = runTest {
        val qrId = 42

        setFavouriteQRCodeUseCase.invoke(qrId)

        coVerify(exactly = 1) { qrCodeRepository.setFavoriteQRCode(qrId) }
    }
}
