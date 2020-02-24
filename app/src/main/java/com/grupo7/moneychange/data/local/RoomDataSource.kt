package com.grupo7.moneychange.data.local

import com.grupo7.data.source.LocalDataSource
import com.grupo7.moneychange.data.mappers.toDataBaseCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.grupo7.domain.Currency as DomainCurrency
import com.grupo7.moneychange.data.local.entity.Currency as RoomCurrency

class RoomDataSource(db: MoneyChangeDb) : LocalDataSource {

    private val historyDao = db.historyDao()
    private val currencyDao = db.currencyDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { currencyDao.currencyCount() <= 0 }

    override suspend fun saveCurrencies(currencies: List<DomainCurrency>) {
        withContext(Dispatchers.IO) { currencyDao.insertCurrencies(currencies.toDataBaseCurrency()) }
    }

    override suspend fun getCurrencies(): List<DomainCurrency> =
        withContext(Dispatchers.IO) { currencyDao.getAll().map { it.toDomainCurrency() } }

    override suspend fun findById(id: Int): DomainCurrency =
        withContext(Dispatchers.IO) { currencyDao.findById(id).toDomainCurrency() }

    override suspend fun update(currency: DomainCurrency) {
        withContext(Dispatchers.IO) { currencyDao.update(currency.toRoomCurrency()) }
    }

    private fun DomainCurrency.toRoomCurrency(): RoomCurrency = RoomCurrency(
        id, description, icon, value
    )

    private fun RoomCurrency.toDomainCurrency(): DomainCurrency = DomainCurrency(
        id, description, icon, value
    )
}