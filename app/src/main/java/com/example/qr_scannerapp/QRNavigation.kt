package com.example.qr_scannerapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qr_scannerapp.composable.HistoryQRCodesScanScreen
import com.example.qr_scannerapp.composable.SavedFavoriteQRCodesScreen
import com.example.qr_scannerapp.composable.ScanQrScreen

@Composable
fun QRNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "scanQr") {
        composable("scanQr") {
            ScanQrScreen(navController = navController)
        }
        composable("savedFavouritesQRCodes") {
            SavedFavoriteQRCodesScreen(navController = navController)
        }
        composable("savedQRCodes") {
            HistoryQRCodesScanScreen(navController = navController)
        }
    }
}
