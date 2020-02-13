package com.grupo7.moneychange.data.repository.local

import com.grupo7.moneychange.data.local.entity.Currency

interface CurrencyDataSource {

    suspend fun insert(currency: Currency)
    suspend fun getAll(): List<Currency>
}