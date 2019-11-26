package com.grupo7.moneychange.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.grupo7.moneychange.R
import kotlinx.android.synthetic.main.activity_main.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {

    var list_of_items = arrayOf("USD", "COP", "EUR")

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, R.layout.spinner_item, list_of_items)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        conversion_from_spinner!!.adapter = adapter
        conversion_to_spinner!!.adapter = adapter
    }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        GlobalScope.launch(Dispatchers.Main) {
            val location = getLocation()
            countryName?.text = getCountryFromLocation(location)
        }

    }

    //region location
    private suspend fun getLocation(): Location? {
        val success = requestPermission()
        return if (success) findLastLocation() else null
    }

    private suspend fun requestPermission(): Boolean =
        suspendCancellableCoroutine { continuation ->
            Dexter
                .withActivity(this@MainActivity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : BasePermissionListener() {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        continuation.resume(true)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        continuation.resume(false)
                    }
                }).check()
        }

    private fun getCountryFromLocation(location: Location?): String {
        val geocoder = Geocoder(this)
        val fromLocation = location?.let {
            geocoder.getFromLocation(location.latitude, location.longitude, 1)
        }
        return fromLocation?.firstOrNull()?.countryName ?: DEFAULT_COUNTRY_NAME

    }

    @SuppressLint("MissingPermission")
    private suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener {
                continuation.resume(it.result)
            }
        }
//endregion

    companion object {
        const val DEFAULT_COUNTRY_NAME = "Rusia"
    }

}
