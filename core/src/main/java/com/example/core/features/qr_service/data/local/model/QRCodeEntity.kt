package com.example.core.features.qr_service.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "qr_codes")
data class QRCodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "qr_code") val qrCode: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = true

)