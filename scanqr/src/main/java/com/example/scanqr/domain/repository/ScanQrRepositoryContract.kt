package com.example.scanqr.domain.repository

import android.app.Activity
import android.content.Intent

interface ScanQrRepositoryContract {

    suspend fun scanQRCode(activity: Activity): String
     fun handleResult(requestCode: Int, resultCode: Int, data: Intent?, callback: (String?) -> Unit)
}