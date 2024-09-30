package com.sunil.runtimepermissionsdk

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : Activity(), PermissionListener {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        askPermission()
    }

    private fun askPermission() {

        if (PermissionHelper.askCameraVideoPermission(this,100))
               showLongToast("Permission Granted")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(
            activity = this,
            requestCode = requestCode,
            grantResults = grantResults,
            permissions = permissions,
            listener = this
        )
    }

    override fun onPermissionGranted(requestCode: Int) {

        when(requestCode){
            100-> if (PermissionHelper.askPostNotificationsPermission(this,101)){
                      Log.d("MainActivity","Ask PostNotificationsPermission")
            } else showLongToast("onPermissionGranted")
        }

    }

    override fun onPermissionDenied(requestCode: Int) {
        showLongToast("onPermissionDenied  ")
    }

    override fun onPermissionDeniedGotoSettings(requestCode: Int) {
        showAlertDialog()
    }

    override fun shouldShowRequestPermissionRationale(requestCode: Int) {
        when(requestCode){
            100->
                if (PermissionHelper.askCameraVideoPermission(this,100)){
                    Log.d("MainActivity","Ask CameraVideoPermission") }
            101-> if (PermissionHelper.askPostNotificationsPermission(this,101)){
                Log.d("MainActivity","Ask PostNotificationsPermission")
            }
        }
    }

    override fun onPermissionDeniedWithNeverAskAgain(requestCode: Int) {
        showLongToast("onPermissionDeniedWithNeverAskAgain")
    }

    private fun showLongToast(msg: String) {
        this.runOnUiThread { Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show() }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission")
            .setMessage("Permission is needed in order to show images and videos")
            .setNegativeButton("Cancel") { dialog, _ ->
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }
            .setPositiveButton("OK") { _, _ ->
                PermissionHelper.goToAppSettings(this)
            }
            .show()
    }
}
