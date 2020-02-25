package com.grupo7.moneychange.data.source

import com.grupo7.moneychange.data.network.ResultData
import com.grupo7.moneychange.data.network.response.LiveResponse

interface LiveDataSource {
    suspend fun getAllExchangeRateData(): ResultData<LiveResponse>
}