package com.example.core.features.qr_service.di

import android.content.Context
import androidx.room.Room
import com.example.core.features.qr_service.data.local.AppDatabase
import com.example.core.features.qr_service.data.local.Dao.QRCodeDao
import com.example.core.features.qr_service.data.repository.SaveQRCodeRepositoryImpl
import com.example.core.features.qr_service.domain.repository.SaveQRCodeRepositoryContract
import com.example.core.features.qr_service.domain.usecase.GetAllFavouritesQRCodeUseCase
import com.example.core.features.qr_service.domain.usecase.GetAllQRCodesUseCase
import com.example.core.features.qr_service.domain.usecase.SaveQRCodeUseCase
import com.example.core.features.qr_service.domain.usecase.SetFavouriteQRCodeUseCase
import com.example.core.features.qr_service.domain.usecase.UnSetFavouriteQRCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SaveQRCodeModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "qr_codes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQRCodeDao(appDatabase: AppDatabase): QRCodeDao {
        return appDatabase.qrCodeDao()
    }

    @Provides
    @Singleton
    fun provideQRCodeRepository(qrCodeDao: QRCodeDao): SaveQRCodeRepositoryContract {
        return SaveQRCodeRepositoryImpl(qrCodeDao)
    }

    @Provides
    @Singleton
    fun provideSaveQRCodeUseCase(repository: SaveQRCodeRepositoryContract): SaveQRCodeUseCase {
        return SaveQRCodeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllQRCodesUseCase(repository: SaveQRCodeRepositoryContract): GetAllQRCodesUseCase {
        return GetAllQRCodesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavouritesQRCode(repository:SaveQRCodeRepositoryContract):GetAllFavouritesQRCodeUseCase{
        return GetAllFavouritesQRCodeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetFavouriteQRCodeUseCase(repository: SaveQRCodeRepositoryContract): SetFavouriteQRCodeUseCase {
        return SetFavouriteQRCodeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUnSetFavouriteQRCodeUseCase(repository: SaveQRCodeRepositoryContract): UnSetFavouriteQRCodeUseCase {
        return UnSetFavouriteQRCodeUseCase(repository)
    }
}
