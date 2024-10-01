package com.sunil.runtimepermissionsdk

import android.app.Activity
import android.os.Build

/**
 * This is the helper class which contains all the methods or functions regarding Permissions.
 */
object PermissionHelper {

    fun goToAppSettings(activity: Activity) {
        PermissionManager.goToAppSettings(activity = activity)
    }

    fun askCameraImagePermission(activity: Activity, requestCode: Int): Boolean {
        val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionConst.PERMISSION.CAMERA_AND_READ_MEDIA_IMAGES_13
        } else {
            PermissionConst.PERMISSION.CAMERA_READ_WRITE_EXTERNAL_STORAGE_ARRAY
        }
        val result = permissionArray.find { permission ->
            !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
        }
        if (!result.toBoolean()) {
            PermissionManager.Request.cameraImagesPermission(activity, requestCode)
        }
        return result.toBoolean()
    }

    fun askCameraVideoPermission(activity: Activity, requestCode: Int): Boolean {
        val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionConst.PERMISSION.CAMERA_AND_READ_MEDIA_VIDEO_13
        } else {
            PermissionConst.PERMISSION.CAMERA_READ_WRITE_EXTERNAL_STORAGE_ARRAY
        }
        val result = permissionArray.find { permission ->
            !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
        }
        if (!result.toBoolean()) {
            PermissionManager.Request.cameraVideoPermission(activity, requestCode,permissionArray)
        }

        return result.toBoolean()
    }

    fun askCameraImageVideoPermission(activity: Activity, requestCode: Int): Boolean {
        val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionConst.PERMISSION.CAMERA_IMAGE_VIDEO_13
        } else {
            PermissionConst.PERMISSION.CAMERA_READ_WRITE_EXTERNAL_STORAGE_ARRAY
        }

        val result = permissionArray.find { permission ->
            !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
        }
        if (!result.toBoolean()) {
            PermissionManager.Request.cameraImageVideoPermission(activity, requestCode,permissionArray)
        }

        return result.toBoolean()
    }

    fun askAudioPermission(activity: Activity, requestCode: Int): Boolean {
        val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionConst.PERMISSION.RECORD_AUDIO_13
        } else {
            PermissionConst.PERMISSION.RECORD_AUDIO_READ_WRITE_EXTERNAL_STORAGE_ARRAY
        }

        val result = permissionArray.find { permission ->
            !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
        }
        if (!result.toBoolean()) {
            PermissionManager.Request.cameraAudioPermission(activity, requestCode,permissionArray)
        }

        return result.toBoolean()
    }

    fun askCameraImageAudioVideoPermission(activity: Activity, requestCode: Int): Boolean {
        val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionConst.PERMISSION.CAMERA_IMAGE_AUDIO_VIDEO_13
        } else {
            PermissionConst.PERMISSION.CAMERA_READ_WRITE_IMAGE_AUDIO_VIDEO_ARRAY
        }

        val result = permissionArray.find { permission ->
            !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
        }
        if (!result.toBoolean()) {
            PermissionManager.Request.cameraImageAudioVideoPermission(activity, requestCode,permissionArray)
        }

        return result.toBoolean()
    }

    fun askPostNotificationsPermission(activity: Activity, requestCode: Int): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             val permissionArray =
                PermissionConst.PERMISSION.POST_NOTIFICATION_13


            val result = permissionArray.find { permission ->
                !PermissionManager.checkSelfPermission(activity = activity, permission = permission)
            }
            if (!result.toBoolean()) {
                PermissionManager.Request.postNotificationPermission(activity, requestCode,permissionArray)
            }

            return result.toBoolean()
        }
        return false
    }

    fun onRequestPermissionsResult(
        activity: Activity,
        requestCode: Int,
        grantResults: IntArray,
        permissions: Array<String>?,
        listener: PermissionListener?
    ) {
        if (null != permissions)
            PermissionManager.onRequestPermissionsResult(
                activity = activity,
                requestCode = requestCode,
                grantResults = grantResults,
                permissions = permissions,
                listener = listener!!
            )
    }

    //    fun requestPermissions(
//        activity: Activity,
//        requestCode: Int,
//        permissions: Array<String>,
//
//    ) {
//        PermissionManager.requestPermissions(
//            activity = activity,
//            permissions = permissions,
//            requestCode = requestCode
//        )
//    }
}


