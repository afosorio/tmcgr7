package com.grupo7.moneychange.data.repository.network

import com.grupo7.moneychange.data.network.ResultData
import com.grupo7.moneychange.domain.Currency

interface ConversionRepository {
    suspend fun getAllExchangeRateData(): ResultData<List<Currency>>
}