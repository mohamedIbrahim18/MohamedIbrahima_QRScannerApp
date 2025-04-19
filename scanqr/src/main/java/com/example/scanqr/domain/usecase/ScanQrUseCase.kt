package com.example.scanqr.domain.usecase

import android.app.Activity
import android.content.Intent
import com.example.scanqr.domain.repository.ScanQrRepositoryContract

class ScanQrUseCase(
    private val repository: ScanQrRepositoryContract
) {
    suspend operator fun invoke(activity: Activity): String {
        return repository.scanQRCode(activity)
    }
    fun handleResult(requestCode: Int, resultCode: Int, data: Intent?, callback: (String?) -> Unit) {
        repository.handleResult(requestCode, resultCode, data, callback)
    }
}