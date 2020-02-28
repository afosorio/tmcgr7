package com.grupo7.data.source

interface PermissionChecker {
    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}