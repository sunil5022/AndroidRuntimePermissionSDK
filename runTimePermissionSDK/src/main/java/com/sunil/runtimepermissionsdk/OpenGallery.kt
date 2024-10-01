package com.sunil.runtimepermissionsdk

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore


object OpenGallery {

    fun forVideo(requestCode: Int, activity: Activity, title: String) {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "video/*"
        activity.startActivityForResult(pickIntent, requestCode)
    }

    fun forImage(requestCode: Int, activity: Activity, title: String) {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"
        activity.startActivityForResult(pickIntent, requestCode)
    }

    fun forAudio(requestCode: Int, activity: Activity, title: String) {
        val pickIntent = Intent()
        pickIntent.type = "audio/*"
        pickIntent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityForResult(pickIntent, requestCode)
    }

    fun forImageAndVideo(requestCode: Int, activity: Activity, title: String) {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/* video/*"
        activity.startActivityForResult(pickIntent, requestCode)
    }

    fun forPdfFile(requestCode: Int, activity: Activity, title: String) {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "application/pdf"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        activity.startActivityForResult(Intent.createChooser(intent, "Select PDF"), requestCode)

    }

}