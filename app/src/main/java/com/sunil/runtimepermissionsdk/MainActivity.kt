package com.sunil.runtimepermissionsdk

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File


class MainActivity : Activity(), PermissionListener {

    private val RC_CAMERA_IMAGE_AUDIOVIDEO_PERMISSION = 100
    private val RC_NOTIFICATION_PERMISSION = 101


    private val RC_GALLARY_IMAGE = 101
    private val RC_GALLARY_VIDEO = 102
    private val RC_GALLARY_AUDIO = 103
    private val RC_GALLARY_IMAGE_VIDEO = 104
    private val RC_PDF_FILE = 105

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        askCameraImageAudioVideoPermission()
        initClickOnView()

    }

    private fun initClickOnView() {
        findViewById<Button>(R.id.btnImage).setOnClickListener {
            OpenGallery.forImage(RC_GALLARY_IMAGE, this, "Pick Image")

        }

        findViewById<Button>(R.id.btnVideo).setOnClickListener {
            OpenGallery.forVideo(RC_GALLARY_VIDEO, this, "Pick Video")

        }
        findViewById<Button>(R.id.btnAudio).setOnClickListener {
            OpenGallery.forAudio(RC_GALLARY_AUDIO, this, "Pick Audio")

        }

        findViewById<Button>(R.id.btnImageVideo).setOnClickListener {
            OpenGallery.forImageAndVideo(RC_GALLARY_IMAGE_VIDEO, this, "Pick Audio")

        }

        findViewById<Button>(R.id.btnPdfFile).setOnClickListener {
            OpenGallery.forPdfFile(RC_PDF_FILE, this, "Pick PDF")

        }

        findViewById<Button>(R.id.askPermissionForNotification).setOnClickListener {
            PermissionHelper.askPostNotificationsPermission(this, RC_NOTIFICATION_PERMISSION)

        }
    }


    private fun askCameraImageAudioVideoPermission() {

        if (PermissionHelper.askCameraImageAudioVideoPermission(this, RC_CAMERA_IMAGE_AUDIOVIDEO_PERMISSION))
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            val selectedMediaUri: Uri? = data?.data
            Log.d("onActivityResult selectedMediaUri : ", selectedMediaUri.toString())

            when (requestCode) {
                RC_GALLARY_IMAGE, RC_GALLARY_VIDEO, RC_GALLARY_AUDIO, RC_GALLARY_IMAGE_VIDEO -> {
                    val realPath =
                        selectedMediaUri?.let { GetRealPath.getRealPathFromURI(this, it) }
                    Log.d("onActivityResult realMediaPath : ", realPath!!)
                }

                RC_PDF_FILE -> {
                    val realPath =
                        selectedMediaUri?.let { GetRealPath.getRealPathFromURI(this, it) }
                    Log.d("onActivityResult PDF ", realPath.toString())
                }
            }
        }
    }

    override fun onPermissionGranted(requestCode: Int) {

//        when(requestCode){
//            100-> if (PermissionHelper.askPostNotificationsPermission(this,101)) {
//                      Log.d("MainActivity","Ask PostNotificationsPermission")
//            } else showLongToast("onPermissionGranted")
//        }

    }

    override fun onPermissionDenied(requestCode: Int) {
        showLongToast("onPermissionDenied  ")
    }

    override fun onPermissionDeniedGotoSettings(requestCode: Int) {
        showAlertDialog()
    }

    override fun shouldShowRequestPermissionRationale(requestCode: Int) {
        when (requestCode) {
            RC_CAMERA_IMAGE_AUDIOVIDEO_PERMISSION ->
                if (PermissionHelper.askCameraImageAudioVideoPermission(this, RC_CAMERA_IMAGE_AUDIOVIDEO_PERMISSION)) {
                    Log.d("MainActivity", "Ask CameraVideoPermission")
                }

            RC_NOTIFICATION_PERMISSION -> if (PermissionHelper.askPostNotificationsPermission(this, RC_NOTIFICATION_PERMISSION)) {
                Log.d("MainActivity", "Ask PostNotificationsPermission")
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