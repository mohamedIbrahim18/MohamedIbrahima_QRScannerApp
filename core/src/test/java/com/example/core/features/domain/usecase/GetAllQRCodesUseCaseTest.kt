package com.example.core.features.domain.usecase

import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.GetAllQRCodesUseCase
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest


class GetAllQRCodesUseCaseTest {
    private lateinit var getAllQRCodesUseCase: GetAllQRCodesUseCase
    private val qrCodeRepository: SaveQRCodeRepositoryContract = mockk()

    @Before
    fun setUp() {
        getAllQRCodesUseCase = GetAllQRCodesUseCase(qrCodeRepository)
    }

    @Test
    fun `should return list of all QR codes from repository`() = runTest {
        val expectedList = listOf(
            QrCodeModel(id = 1, qrCode = "https://example.com", isFavorite = true),
            QrCodeModel(id = 2, qrCode = "https://google.com", isFavorite = false)
        )
        coEvery { qrCodeRepository.getAllQRCodes() } returns expectedList

        val result = getAllQRCodesUseCase.execute()

        assertEquals(expectedList, result)
        coVerify(exactly = 1) { qrCodeRepository.getAllQRCodes() }
    }

    @Test
    fun `should return empty list when no QR codes found`() = runTest {
        coEvery { qrCodeRepository.getAllQRCodes() } returns emptyList()

        val result = getAllQRCodesUseCase.execute()

        assertEquals(emptyList<QrCodeModel>(), result)
        coVerify { qrCodeRepository.getAllQRCodes() }
    }
}