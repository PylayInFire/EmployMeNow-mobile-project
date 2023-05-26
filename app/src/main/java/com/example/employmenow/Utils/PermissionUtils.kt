package com.example.employmenow.Utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.Manifest

class PermissionUtils {

    companion object {
        fun checkPermissions(activity: Activity): Boolean {

            val storagePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

            val audioPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED // На всякий случай запрошу хотя это не обязательно если у нас интент

            return storagePermission && audioPermission
        }

        fun requestPermissions(activity: Activity) {
            activity.requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
                ),
                300
            )
        }
    }
}