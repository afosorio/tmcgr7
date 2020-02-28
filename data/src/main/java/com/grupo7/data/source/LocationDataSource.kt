package com.grupo7.data.source

interface LocationDataSource {
    suspend fun findLastLocation(): String?
}