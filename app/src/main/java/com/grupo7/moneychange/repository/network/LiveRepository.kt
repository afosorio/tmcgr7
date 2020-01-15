package com.grupo7.moneychange.repository.network

import com.grupo7.moneychange.data.network.models.LiveResponse

interface LiveRepository {
    suspend fun getLive(): LiveResponse?
}