package com.example.data.source

interface CountryRepository {
    suspend fun getCountryLocation(/*permissionChecker: PermissionChecker*/): String
}