package com.grupo7.data.source

interface CountryRepository {
    suspend fun getCountryLocation(/*permissionChecker: PermissionChecker*/): String
}