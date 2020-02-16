package com.grupo7.moneychange.data.repository.network

import com.grupo7.domain.Currency
import com.grupo7.moneychange.data.mappers.toDataBaseCurrency
import com.grupo7.moneychange.data.mappers.toDomainCurrency
import com.grupo7.moneychange.data.network.ResultData
import com.grupo7.moneychange.data.repository.local.CurrencyRepository
import com.grupo7.moneychange.data.source.LiveDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ConversionRepositoryImpl(
    private val liveDataSource: LiveDataSource,
    private val currentRepository: CurrencyRepository
) : ConversionRepository {

    override suspend fun getAllExchangeRateData(): ResultData<List<Currency>> =
        withContext(Dispatchers.IO) {
            when (val result = liveDataSource.getAllExchangeRateData()) {
                is ResultData.Success -> {
                    if (result.data.success == true && !result.data.quotes.isNullOrEmpty()) {
                        val data = result.data.quotes.toDomainCurrency()
                        currentRepository.insertCurrencyList(data.toDataBaseCurrency())
                        ResultData.Success(currentRepository.getAll().toDomainCurrency())
                    } else {
                        ResultData.Error(IOException("data vacia"))
                    }
                }
                else -> result as ResultData.Error
            }
        }
}