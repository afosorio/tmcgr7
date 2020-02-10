package com.example.data.source

interface PermissionChecker {
    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}