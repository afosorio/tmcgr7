package com.grupo7.moneychange.datasources

import com.grupo7.data.source.LocalCurrencyDataSource
import com.grupo7.domain.Currency

class FakeLocalDataSource : LocalCurrencyDataSource {
    var currency: List<Currency> = emptyList()
    override suspend fun isEmpty() = currency.isEmpty()
    override suspend fun saveCurrencies(currencies: List<Currency>) {
        this.currency = currencies
    }

    override suspend fun getCurrencies(): List<Currency> = currency

    override suspend fun findById(id: Int): Currency = currency.first { it.id == id }

    override suspend fun update(currency: Currency) {
        this.currency = this.currency.filterNot { it.id == currency.id } + currency
    }
}