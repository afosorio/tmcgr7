package com.grupo7.moneychange.data.local.dataSourceImpl

import com.grupo7.data.source.LocalCurrencyDataSource
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.mappers.toDataBaseCurrency
import com.grupo7.moneychange.data.mappers.toDomainCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.grupo7.domain.Currency as DomainCurrency

class LocalCurrencyDataSourceImpl(db: MoneyChangeDb) : LocalCurrencyDataSource {

    private val currencyDao = db.currencyDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { currencyDao.currencyCount() <= 0 }

    override suspend fun saveCurrencies(currencies: List<DomainCurrency>) {
        withContext(Dispatchers.IO) { currencyDao.insert(currencies.toDataBaseCurrency()) }
    }

    override suspend fun getCurrencies(): List<DomainCurrency> =
        withContext(Dispatchers.IO) { currencyDao.getAll().toDomainCurrency() }

    override suspend fun findById(id: Int): DomainCurrency =
        withContext(Dispatchers.IO) { currencyDao.findById(id).toDomainCurrency() }

    override suspend fun update(currency: DomainCurrency) {
        withContext(Dispatchers.IO) { currencyDao.update(currency.toDataBaseCurrency()) }
    }
}