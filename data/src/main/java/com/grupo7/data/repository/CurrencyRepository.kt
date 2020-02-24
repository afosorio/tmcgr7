package com.grupo7.data.repository

import com.grupo7.data.source.LocalDataSource
import com.grupo7.data.source.RemoteDataSource
import com.grupo7.domain.Currency

class CurrencyRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getCurrencies(): ResultData<List<Currency>> {
        if (localDataSource.isEmpty()) {
            when (val result = remoteDataSource.getAllExchangeRateData()) {
                is ResultData.Success -> localDataSource.saveCurrencies(result.data)
                is ResultData.Error -> return result
            }
        }
        return ResultData.Success(localDataSource.getCurrencies())
    }

    suspend fun findById(id: Int): Currency = localDataSource.findById(id)

    suspend fun update(currency: Currency) = localDataSource.update(currency)

}