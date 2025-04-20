package com.example.qr_scannerapp.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qr_scannerapp.viewmodel.QrServiceViewModel

@Composable
fun SavedFavoriteQRCodesScreen(viewModel: QrServiceViewModel = hiltViewModel(), navController: NavController) {
    val savedQRCodes = viewModel.savedQRCodes.value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchSavedQRCodes()
    }

    LaunchedEffect(savedQRCodes) {
        Toast.makeText(context, "Saved QR Codes: ${savedQRCodes.size}", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saved QR Codes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        savedQRCodes.forEach { qrCode ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(text = "QR Code: ${qrCode.qrCode}", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(8.dp))

                IconButton(
                    onClick = { viewModel.toggleFavorite(qrCode) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (qrCode.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        },
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2473B3)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Back to Scan QR", color = Color.White, fontSize = 18.sp)
        }
    }
}

