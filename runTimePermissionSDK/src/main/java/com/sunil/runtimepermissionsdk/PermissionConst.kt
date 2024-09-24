package com.sunil.runtimepermissionsdk

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.RECORD_AUDIO
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * All data and constants regarding the Permissions are listed here
 */
interface PermissionConst {

    object REQUEST_CODE {
        internal const val CAMERA_IMAGE = 101
        internal const val GALLERY_IMAGE = 102
        internal const val CAMERA_AND_GALLERY_IMAGE = 103

        internal const val LOCATION = 104

        const val LOCATION_AND_FOREGROUND_SERVICE = 101
        const val GPS = 102
        const val SETTINGS = 103

        internal const val READ_WRITE_STORAGE = 109
        internal const val VIDEO_CAMERA_AND_GALLERY_IMAGE = 110

    }

    object PERMISSION {
        private const val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        const val CAMERA = Manifest.permission.CAMERA
        private const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION

        internal val CAMERA_READ_WRITE_EXTERNAL_STORAGE_ARRAY =
            arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)

        internal val CAMERA_READ_WRITE_IMAGE_AUDIO_VIDEO_ARRAY =
            arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE,RECORD_AUDIO)

        internal val RECORD_AUDIO_READ_WRITE_EXTERNAL_STORAGE_ARRAY =
            arrayOf(RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        internal val CAMERA_AND_READ_MEDIA_IMAGES_13 =
            arrayOf(CAMERA, READ_MEDIA_IMAGES)

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        internal val CAMERA_AND_READ_MEDIA_VIDEO_13 =
            arrayOf(CAMERA, READ_MEDIA_VIDEO)

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        internal val CAMERA_IMAGE_AUDIO_VIDEO_13 =
            arrayOf(CAMERA,READ_MEDIA_IMAGES,RECORD_AUDIO,READ_MEDIA_VIDEO)

        internal val RECORD_AUDIO_13 =
            arrayOf(RECORD_AUDIO)

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        internal val CAMERA_IMAGE_VIDEO_13 =
            arrayOf(CAMERA, READ_MEDIA_IMAGES,READ_MEDIA_VIDEO)

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        internal val POST_NOTIFICATION_13 =
            arrayOf(POST_NOTIFICATIONS)

        const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION

        const val FOREGROUND_SERVICE = Manifest.permission.FOREGROUND_SERVICE

        val LOCATION_ARRAY = arrayOf(FINE_LOCATION, COARSE_LOCATION)
        val LOCATION_AND_FOREGROUND_SERVICE_ARRAY = arrayOf(FINE_LOCATION, COARSE_LOCATION, FOREGROUND_SERVICE)

    }

}
