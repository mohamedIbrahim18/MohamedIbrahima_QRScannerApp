package com.example.scanqr.data.datasource.contract

import android.app.Activity
import android.content.Intent

interface QRCodeScanner {
    fun scanQRCode(activity: Activity, callback: (String?) -> Unit)
    fun handleResult(requestCode: Int, resultCode: Int, data: Intent?)
}