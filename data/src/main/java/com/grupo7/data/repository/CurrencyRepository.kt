package com.grupo7.data.repository

import com.grupo7.data.ResultData
import com.grupo7.data.source.LocalCurrencyDataSource
import com.grupo7.data.source.RemoteCurrencyDataSource
import com.grupo7.domain.Currency

class CurrencyRepository(
    private val localCurrencyDataSource: LocalCurrencyDataSource,
    private val remoteCurrencyDataSource: RemoteCurrencyDataSource
) {

    suspend fun getCurrencies(): ResultData<List<Currency>> {
        if (localCurrencyDataSource.isEmpty()) {
            when (val result = remoteCurrencyDataSource.getAllExchangeRateData()) {
                is ResultData.Success -> localCurrencyDataSource.saveCurrencies(result.data)
                is ResultData.Error -> return result
            }
        }
        return ResultData.Success(localCurrencyDataSource.getCurrencies())
    }

    suspend fun findById(id: Int): Currency = localCurrencyDataSource.findById(id)

    suspend fun update(currency: Currency) = localCurrencyDataSource.update(currency)

}