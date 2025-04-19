package com.example.core.features.qr_service.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.features.qr_service.data.local.Dao.QRCodeDao
import com.example.core.features.qr_service.data.local.model.QRCodeEntity

@Database(entities = [QRCodeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun qrCodeDao(): QRCodeDao
}