package com.grupo7.moneychange.datasources

import com.grupo7.data.source.LocationDataSource

class FakeLocationDataSource : LocationDataSource {
    var location = "CO"

    override suspend fun findLastLocation(): String? = location
}