package com.grupo7.data.source

import com.grupo7.data.repository.ResultData
import com.grupo7.domain.LiveResponse

interface RemoteDataSource {
    suspend fun getAllExchangeRateData(): ResultData<LiveResponse>
}