package com.sunil.runtimepermissionsdk

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat

object PermissionManager {

    private var listener: PermissionListener? = null

    internal fun setPermissionListener(listener: PermissionListener?) {
        this.listener = listener
    }

    private fun useRunTimePermissions(): Boolean =
        Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1

    internal fun checkSelfPermission(activity: Activity, permission: String): Boolean =
        if (useRunTimePermissions())
            activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        else true


    internal fun requestPermissions(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int,
    ) {
        if (useRunTimePermissions())
            activity.requestPermissions(permissions, requestCode)
    }

    internal fun goToAppSettings(activity: Activity) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.packageName, null)
        ).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        activity.startActivity(intent)
    }

    internal fun onRequestPermissionsResult(
        activity: Activity,
        requestCode: Int,
        grantResults: IntArray,
        permissions: Array<String>,
        listener: PermissionListener
    ) {
        val isPermissionDenied =
            grantResults.filter { result -> result == PackageManager.PERMISSION_DENIED }.any()

        when {

            isPermissionDenied -> {
                for (i in permissions.indices) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            activity,
                            permissions[i]
                        )
                    ) {
                        //Request.cameraVideoPermission(activity, requestCode,permissions)4
                        listener.shouldShowRequestPermissionRationale(requestCode=requestCode)

                        return
                    } else {
                        listener.onPermissionDeniedGotoSettings(requestCode = requestCode)
                        return
                    }
                }
            }

            hasPermissions(activity = activity, permissions = permissions) ->
                listener.onPermissionGranted(requestCode = requestCode)

            else ->
                listener.onPermissionDenied(requestCode = requestCode)
        }

        setPermissionListener(listener = listener)
    }

    private fun hasPermissions(activity: Activity, permissions: Array<String>): Boolean =
        if (useRunTimePermissions())
            !permissions.filter { permission ->
                !checkSelfPermission(activity = activity, permission = permission)
            }.any()
        else true

    object Request {
        internal fun cameraImagesPermission(
            activity: Activity,
            requestCode: Int,
        ) {
            val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                PermissionConst.PERMISSION.CAMERA_AND_READ_MEDIA_IMAGES_13
            } else {
                PermissionConst.PERMISSION.CAMERA_READ_WRITE_EXTERNAL_STORAGE_ARRAY
            }
            requestPermissions(
                activity = activity,
                permissions = permissionArray,
                requestCode = requestCode
            )
        }

        internal fun cameraVideoPermission(
            activity: Activity,
            requestCode: Int,
            permissionArray: Array<String>,

            ) {

            requestPermissions(
                activity = activity,
                permissions = permissionArray,
                requestCode = requestCode
            )
        }

        internal fun cameraImageVideoPermission(
            activity: Activity,
            requestCode: Int,
            permissionArray: Array<String>,

            ) {
            requestPermissions(
                activity = activity,
                permissions = permissionArray,
                requestCode = requestCode
            )
        }

        internal fun cameraAudioPermission(
            activity: Activity,
            requestCode: Int,
            permissionArray: Array<String>,

            ) {

            requestPermissions(
                activity = activity,
                permissions = permissionArray,
                requestCode = requestCode
            )
        }

        internal fun cameraImageAudioVideoPermission(
            activity: Activity,
            requestCode: Int,
            permissionArray: Array<String>,
        ) {

            requestPermissions(
                activity = activity,
                permissions = permissionArray,
                requestCode = requestCode
            )
        }

        internal fun postNotificationPermission(
            activity: Activity,
            requestCode: Int,
            permissionArray: Array<String>,
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(
                    activity = activity,
                    permissions = permissionArray,
                    requestCode = requestCode
                )
            }
        }

    }
}