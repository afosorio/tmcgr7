package com.grupo7.moneychange.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class CountryRepositoryImpl(context: Context) : CountryRepository {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val geocoder = Geocoder(context)

    override suspend fun getCountryLocation(permissionChecker: PermissionChecker): String {
        return getLocation(permissionChecker).toCountry()
    }

    private suspend fun getLocation(permissionChecker: PermissionChecker): Location? {
        val success = permissionChecker.requestPermission()
        return if (success) findLastLocation() else null
    }

    @SuppressLint("MissingPermission")
    private suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener {
                continuation.resume(it.result)
            }
        }

    private fun Location?.toCountry(): String {
        val country = this?.let {
            try {
                geocoder.getFromLocation(latitude, longitude, 1)
            } catch (ex: Exception){
                null
            }
        }
        return country?.firstOrNull()?.countryName ?: DEFAULT_COUNTRY_NAME

    }

    companion object {
        const val DEFAULT_COUNTRY_NAME = "Rusia"
    }


}