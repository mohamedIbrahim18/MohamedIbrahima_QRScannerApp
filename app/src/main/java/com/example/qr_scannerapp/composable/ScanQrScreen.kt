package com.example.qr_scannerapp.composable

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qr_scannerapp.viewmodel.ScanQrViewModel

@Composable
fun ScanQrScreen(viewModel: ScanQrViewModel = hiltViewModel(), navController: NavController) {
    val savedQRCodes = viewModel.savedQRCodes.value
    val activity = LocalActivity.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                activity?.let { viewModel.scanQrCode(it) }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2473B3)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Scan QR", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("savedFavouritesQRCodes")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2473B3)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Favourites QR Codes", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("savedQRCodes")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2473B3)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "History QR Codes", color = Color.White, fontSize = 18.sp)
        }
    }
}


