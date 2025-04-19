package com.example.scanqr.di

import com.example.scanqr.data.repository.ScanQrRepositoryImpl
import com.example.scanqr.data.datasource.contract.QRCodeScanner
import com.example.scanqr.data.datasource.implementation.ZXingQRCodeScanner
import com.example.scanqr.domain.repository.ScanQrRepositoryContract
import com.example.scanqr.domain.usecase.ScanQrUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScanQrModule {

    @Provides
    @Singleton
    fun provideQRCodeScanner(): QRCodeScanner {
        return ZXingQRCodeScanner()
    }

    @Provides
    @Singleton
    fun provideScanQrRepository(scanner: QRCodeScanner): ScanQrRepositoryContract {
        return ScanQrRepositoryImpl(scanner)
    }

    @Provides
    @Singleton
    fun provideScanQrUseCase(repository: ScanQrRepositoryContract): ScanQrUseCase {
        return ScanQrUseCase(repository)
    }
}
