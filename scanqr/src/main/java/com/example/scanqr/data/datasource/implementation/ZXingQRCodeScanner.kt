package com.example.scanqr.data.datasource.implementation

import android.app.Activity
import android.content.Intent
import com.example.scanqr.data.datasource.contract.QRCodeScanner
import com.google.zxing.integration.android.IntentIntegrator

class ZXingQRCodeScanner : QRCodeScanner {
    private var callback: ((String?) -> Unit)? = null

    override fun scanQRCode(activity: Activity, callback: (String?) -> Unit) {
        this.callback = callback

        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a qr code please ")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(false)
        integrator.setCaptureActivity(MyCaptureActivity::class.java)
        integrator.initiateScan()
    }

    override fun handleResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            callback?.invoke(result.contents)
            callback = null
        } else {
            callback?.invoke(null)
            callback = null
        }
    }
}