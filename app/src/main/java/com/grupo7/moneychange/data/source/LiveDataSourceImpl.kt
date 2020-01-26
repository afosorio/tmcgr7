package com.grupo7.moneychange.data.source

import com.grupo7.moneychange.data.network.ResultData
import com.grupo7.moneychange.data.network.callServices
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.response.LiveResponse
import com.grupo7.moneychange.data.network.safeApiCall
import com.grupo7.moneychange.data.repository.network.Constans

class LiveDataSourceImpl(private val liveApi: LiveApi) : LiveDataSource {

    override suspend fun getAllExchangeRateData(): ResultData<LiveResponse> =
        safeApiCall(
            call = { liveApi.getLive(Constans.Key.ACCESS_KEY).callServices() },
            errorMessage = "Algo fallo al llamar al servicio '../live'"
        )
}