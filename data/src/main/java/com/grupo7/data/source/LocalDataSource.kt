package com.grupo7.data.source

import com.grupo7.domain.Currency

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveCurrencies(currencies: List<Currency>)
    suspend fun getCurrencies(): List<Currency>
    suspend fun findById(id: Int): Currency
    suspend fun update(currency: Currency)
}