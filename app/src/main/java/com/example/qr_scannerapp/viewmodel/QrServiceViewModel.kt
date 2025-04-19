package com.example.qr_scannerapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.features.qr_service.domain.model.QrCodeModel
import com.example.core.features.qr_service.domain.usecase.GetAllFavouritesQRCodeUseCase
import com.example.core.features.qr_service.domain.usecase.GetAllQRCodesUseCase
import com.example.core.features.qr_service.domain.usecase.SetFavouriteQRCodeUseCase
import com.example.core.features.qr_service.domain.usecase.UnSetFavouriteQRCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrServiceViewModel @Inject constructor(
    private val getAllQRCodesUseCase: GetAllQRCodesUseCase,
    private val setFavouriteQRCodeUseCase: SetFavouriteQRCodeUseCase,
    private val unSetFavouriteQRCodeUseCase: UnSetFavouriteQRCodeUseCase,
    private val getAllFavouritesQRCodeUseCase: GetAllFavouritesQRCodeUseCase
) : ViewModel() {
    private val _savedQRCodes = mutableStateOf<List<QrCodeModel>>(emptyList())
    val savedQRCodes: State<List<QrCodeModel>> = _savedQRCodes

    fun fetchSavedQRCodes() {
        viewModelScope.launch {
            val codes = getAllFavouritesQRCodeUseCase.execute()
            _savedQRCodes.value = codes
        }
    }
    fun toggleFavorite(qrCode: QrCodeModel) {
        viewModelScope.launch {
            if (qrCode.isFavorite) {
                unSetFavouriteQRCodeUseCase.invoke(qrCode.id)
            } else {
                setFavouriteQRCodeUseCase.invoke(qrCode.id)
            }
            fetchSavedQRCodes()
        }
    }
}
