package com.example.core.features.domain.usecase

import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.GetAllFavouritesQRCodeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllFavouritesQRCodeUseCaseTest {

    private lateinit var useCase: GetAllFavouritesQRCodeUseCase
    private val repository: SaveQRCodeRepositoryContract = mockk()

    @Before
    fun setUp() {
        useCase = GetAllFavouritesQRCodeUseCase(repository)
    }

    @Test
    fun `should return list of favourite QR codes from repository`() = runTest {
        val expectedList = listOf(
            QrCodeModel(id = 1, qrCode = "https://example.com", isFavorite = true),
            QrCodeModel(id = 2, qrCode = "https://another.com", isFavorite = true)
        )
        coEvery { repository.getFavoriteQRCodes() } returns expectedList

        val result = useCase.execute()

        assertEquals(expectedList, result)
        coVerify(exactly = 1) { repository.getFavoriteQRCodes() }
    }

    @Test
    fun `should return empty list when no favourites are found`() = runTest {
        coEvery { repository.getFavoriteQRCodes() } returns emptyList()

        val result = useCase.execute()

        assertEquals(emptyList<QrCodeModel>(), result)
        coVerify { repository.getFavoriteQRCodes() }
    }
}
