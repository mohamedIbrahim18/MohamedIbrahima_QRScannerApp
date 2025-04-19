package com.example.scanqr.data.repository

import android.app.Activity
import android.content.Intent
import com.example.scanqr.data.datasource.contract.QRCodeScanner
import com.example.scanqr.domain.repository.ScanQrRepositoryContract
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ScanQrRepositoryImpl(
    private val scanner : QRCodeScanner
) :ScanQrRepositoryContract {
    override suspend fun scanQRCode(activity: Activity): String {
        return suspendCoroutine { continuation ->
            scanner.scanQRCode(activity) { result ->
                continuation.resume(result ?: "")
            }
        }
    }

    override fun handleResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        callback: (String?) -> Unit
    ) {
        scanner.handleResult(requestCode, resultCode, data)

    }
}