package com.example.qr_scannerapp.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanqr.domain.usecase.ScanQrUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.usecase.SaveQRCodeUseCase

@HiltViewModel
class ScanQrViewModel @Inject constructor(
    private val scanQrUseCase: ScanQrUseCase,
    private val saveQRCodeUseCase: SaveQRCodeUseCase,
) : ViewModel() {

    private val _scanResult = mutableStateOf<String?>(null)
    val scanResult: State<String?> = _scanResult

    private val _savedQRCodes = mutableStateOf<List<QrCodeModel>>(emptyList())
    val savedQRCodes: State<List<QrCodeModel>> = _savedQRCodes

    fun scanQrCode(activity: Activity) {
        viewModelScope.launch {
            Log.d("ScanQrViewModel", "Calling scanQrUseCase...")
            val result = scanQrUseCase(activity)
            Log.d("ScanQrViewModel", "Result: $result")
            if (result != null) {
                _scanResult.value = result
                saveQRCodeUseCase.invoke(QrCodeModel(qrCode = result, isFavorite = true))
            } else {
                Log.e("ScanQrViewModel", "Failed to scan QR Code")
            }
        }
    }
    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        scanQrUseCase.handleResult(requestCode, resultCode, data) { result ->
            viewModelScope.launch {
                if (result != null) {
                    _scanResult.value = result
                    saveQRCodeUseCase.invoke(QrCodeModel(qrCode = result, isFavorite = true))
                } else {
                    Log.e("ScanQrViewModel", "No QR Code found")
                }
            }
        }
    }



}
