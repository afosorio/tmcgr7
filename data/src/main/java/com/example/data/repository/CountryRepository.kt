package com.example.data.repository

import com.example.data.source.CountryRepository
import com.example.data.source.LocationDataSource
import com.example.data.source.PermissionChecker

class CountryRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker
) : CountryRepository {
    companion object {
        const val DEFAULT_COUNTRY_NAME = "Rusia"
    }


    override suspend fun getCountryLocation(): String {
        return if (permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            locationDataSource.findLastLocation() ?: DEFAULT_COUNTRY_NAME
        } else {
            DEFAULT_COUNTRY_NAME
        }
    }


}