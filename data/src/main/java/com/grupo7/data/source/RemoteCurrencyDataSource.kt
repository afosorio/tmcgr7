package com.grupo7.data.source

import com.grupo7.data.ResultData
import com.grupo7.domain.Currency

interface RemoteCurrencyDataSource {
    suspend fun getAllExchangeRateData(): ResultData<List<Currency>>
}