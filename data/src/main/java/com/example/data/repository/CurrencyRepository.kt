package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Currency

class CurrencyRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String
) {

    suspend fun getCurrencies(): List<Currency> {
        if (localDataSource.isEmpty()) {
            val coins = remoteDataSource.getLive(apiKey).quotes
            val currencies = coins.map { Currency(0, it.key.substring(3, 6), "", it.value) }
            localDataSource.saveCurrencies(currencies)
        }
        return localDataSource.getCurrencies()
    }

    suspend fun findById(id: Int): Currency = localDataSource.findById(id)

    suspend fun update(currency: Currency) = localDataSource.update(currency)
}