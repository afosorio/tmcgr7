package com.example.data.source

import com.example.domain.LiveResponse

interface RemoteDataSource {
    suspend fun getLive(apiKey: String): LiveResponse
}