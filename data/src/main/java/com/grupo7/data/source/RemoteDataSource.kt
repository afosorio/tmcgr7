package com.grupo7.data.source

import com.grupo7.domain.LiveResponse

interface RemoteDataSource {
    suspend fun getLive(apiKey: String): LiveResponse
}