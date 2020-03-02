package com.grupo7.moneychange.utils

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.grupo7.data.source.PermissionChecker

class AndroidPermissionChecker(private val activity: Application) : PermissionChecker {

    override suspend fun check(permission: PermissionChecker.Permission): Boolean =
        ContextCompat.checkSelfPermission(
            activity,
            permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED

    private fun PermissionChecker.Permission.toAndroidId() = when (this) {
        PermissionChecker.Permission.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
    }
}

