package com.example.data.source

import com.example.domain.LiveResponse

interface RemoteDataSource {
    fun getLive(apiKey: String): LiveResponse
}