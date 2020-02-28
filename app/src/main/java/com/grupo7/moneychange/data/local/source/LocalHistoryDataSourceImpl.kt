package com.grupo7.moneychange.data.local.source

import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.domain.History
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.mappers.toDomainHistory
import com.grupo7.moneychange.data.mappers.toLocalHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalHistoryDataSourceImpl(db: MoneyChangeDb) : LocalHistoryDataSource {

    private val historyDao = db.historyDao()

    override suspend fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveHistory(history: History) = withContext(Dispatchers.IO) {
        historyDao.insert(history.toLocalHistory())
    }


    override suspend fun getHistory(): List<History> = withContext(Dispatchers.IO) {
        historyDao.getAll().map { it.toDomainHistory() }
    }


    override suspend fun findById(id: Int): History = withContext(Dispatchers.IO) {
        historyDao.findById(id).toDomainHistory()
    }


    override suspend fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}