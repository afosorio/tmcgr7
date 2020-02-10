package com.example.data.source

interface LocationDataSource {
    suspend fun findLastLocation(): String?
}