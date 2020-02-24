package com.grupo7.moneychange.data.network.source

import com.grupo7.data.repository.ResultData
import com.grupo7.data.source.RemoteDataSource
import com.grupo7.domain.Currency
import com.grupo7.moneychange.data.mappers.toDomainCurrency
import com.grupo7.moneychange.data.network.callServices
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.response.LiveResponse
import com.grupo7.moneychange.data.network.safeApiCall
import com.grupo7.moneychange.data.repository.network.Constans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RemoteDataSourceImpl(private val liveApi: LiveApi) : RemoteDataSource {
    override suspend fun getAllExchangeRateData(): ResultData<List<Currency>> = withContext(Dispatchers.IO) {
        safeApiCall(
            call = { renderData(liveApi.getLive(Constans.Key.ACCESS_KEY).callServices()) },
            errorMessage = "Algo fallo al llamar al servicio '../live'"
        )
    }


    private fun renderData(resultData: ResultData<LiveResponse>): ResultData<List<Currency>> =
        when (resultData) {
            is ResultData.Success -> if (resultData.data.success == true) {
                ResultData.Success(resultData.data.quotes.toDomainCurrency())
            } else {
                ResultData.Error(IOException(resultData.data.error?.info.toString()))
            }
            is ResultData.Error -> resultData
        }
}