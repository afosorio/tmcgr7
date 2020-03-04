package com.grupo7.moneychange.datasources

import com.grupo7.data.source.PermissionChecker

class FakePermissionChecker : PermissionChecker {
    var permissionGranted = true

    override suspend fun check(permission: PermissionChecker.Permission): Boolean =
        permissionGranted
}