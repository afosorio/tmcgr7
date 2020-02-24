package com.grupo7.data.source

import com.grupo7.data.repository.ResultData
import com.grupo7.domain.Currency

interface RemoteDataSource {
    suspend fun getAllExchangeRateData(): ResultData<List<Currency>>
}