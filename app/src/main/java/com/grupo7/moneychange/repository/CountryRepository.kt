package com.grupo7.moneychange.repository

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class CountryRepository(activity: Activity) {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    private val geocoder = Geocoder(activity)
    private val permissionChecker = PermissionChecker(activity, ACCESS_FINE_LOCATION)
    //region location

    suspend fun currentCountry(): String = getLocation().toCountry()

    private suspend fun getLocation(): Location? {
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
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return country?.firstOrNull()?.countryName ?: DEFAULT_COUNTRY_NAME

    }

//endregion

    companion object {
        const val DEFAULT_COUNTRY_NAME = "Rusia"
    }


}
