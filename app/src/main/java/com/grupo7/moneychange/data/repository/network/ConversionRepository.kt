package com.grupo7.moneychange.data.repository.network

import com.grupo7.domain.Currency
import com.grupo7.moneychange.data.network.ResultData

interface ConversionRepository {
    suspend fun getAllExchangeRateData(): ResultData<List<Currency>>
}