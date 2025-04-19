package com.example.core.features.qr_service.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.features.qr_service.data.local.model.QRCodeEntity

@Dao
interface QRCodeDao {

    @Insert
    suspend fun insertQRCode(qrCode: QRCodeEntity)

    @Query("SELECT * FROM qr_codes")
    suspend fun getAllQRCodes(): List<QRCodeEntity>

    @Query("SELECT * FROM qr_codes WHERE is_favorite = 1")
    suspend fun getFavoriteQRCodes(): List<QRCodeEntity>

    @Query("UPDATE qr_codes SET is_favorite = 1 WHERE id = :id")
    suspend fun setFavorite(id: Int)

    @Query("UPDATE qr_codes SET is_favorite = 0 WHERE id = :id")
    suspend fun unSetFavorite(id: Int)
}